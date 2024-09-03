/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDetails;
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
public class ShowCandidateControllerServlet extends HttpServlet {

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
           String data = request.getParameter("id");
           String removecid = request.getParameter("remove");
           
           try{
               if(data != null && data.equalsIgnoreCase("cid"))
               {
                   ArrayList<String> candidateList = CandidateDAO.getCandidateId();
                   request.setAttribute("candidateid",candidateList);
                   request.setAttribute("result","candidateList");
                   System.out.println(candidateList);
               }
               else if(removecid != null )
               {
                  CandidateDetails cd = CandidateDAO.getDetailsById(removecid);
                   request.setAttribute("candidate", cd);
                   request.setAttribute("result" ,"details");
                   request.setAttribute("isremove" ,"yes") ; 
                  
               }
               else{
                   CandidateDetails cd = CandidateDAO.getDetailsById(data);
                   request.setAttribute("candidate", cd);
                   request.setAttribute("result" ,"details");
                  
               }
               rd = request.getRequestDispatcher("adminshowcandidate.jsp");
           }
           catch(Exception ex)
           {
               
             ex.printStackTrace();
             request.setAttribute("Exception",ex);
             rd = request.getRequestDispatcher("ShowException.jsp");
             
           }
           finally{
               System.out.println("Inside ShowCandidateControllerServlet");
               if(rd != null)
               rd.forward(request, response); 
               else
                   System.out.println("Could'nt forward the request inside ShowCandidateControllerServlet");
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
