/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.RegistrationDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author asus
 */
public class RegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = null;
         
        UserDetails user = new UserDetails() ;
        
        
         user.setUserId(request.getParameter("userid"));
         user.setPassword(request.getParameter("password"));
         user.setUsername(request.getParameter("username"));
         user.setAddress(request.getParameter("address"));
         user.setCity(request.getParameter("city"));
         user.setEmail(request.getParameter("email"));
         user.setMobile(Long.parseLong(request.getParameter("mobile")));
                 

        
        try{
             boolean userfound = false, result = false;
             
             if(RegistrationDao.searchUser(user.getUserId())==false)
             {
                result = RegistrationDao.registerUser(user);
             }
             else
             userfound = true;
             
             request.setAttribute("userfound",userfound);
             request.setAttribute("result",result);
             request.setAttribute("username",user.getUsername());
             
             rd= request.getRequestDispatcher("RegistrationResponse.jsp");
             
             
             
        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            request.setAttribute("Exception In REGISTRATION SERVLET",sqlex);
            rd = request.getRequestDispatcher("ShowException.jsp");
        }
        finally
        {
            if(rd != null){
                
                System.out.println("Redirecting to Registration Response JSP");
                rd.forward(request,response);
            }
            
            
            else
                System.out.println("ERROR IN REGISTRATION SERVLET");    
        }
        
                
        
        
        
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
