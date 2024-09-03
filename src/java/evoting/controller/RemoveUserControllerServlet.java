/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.UserDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author asus
 */
public class RemoveUserControllerServlet extends HttpServlet {

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
        
           RequestDispatcher rd = null ;
           HttpSession sess = request.getSession();
           String userid = (String)sess.getAttribute("userid");
           if(userid==null)
           {
               sess.invalidate();
               response.sendRedirect("accessdenied.html");
               return ; 
           }
           String adharno = request.getParameter("id");
           String usid = request.getParameter("details");
           try{
                if(adharno != null)
                {
                    boolean ans  =  UserDAO.removeUser(adharno);
                    request.setAttribute("ans",ans);
                    rd = request.getRequestDispatcher("showuserid.jsp");
                }
                else{
                    ArrayList<String> details = UserDAO.getDetails(usid);
                    System.out.println("user details : "+ details);
                    request.setAttribute("details",details);
                    rd = request.getRequestDispatcher("showuserdetails.jsp");
                    
                }
                
            
           
           }
           catch(Exception ex)
           {
              ex.printStackTrace();
              request.setAttribute("Exception",ex);
              rd = request.getRequestDispatcher("ShowException.jsp");
           }
           finally{
                    rd.forward(request,response);
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
