/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.* ;
import java.util.ArrayList;
import org.json.JSONObject;


/**
 *
 * @author asus
 */
public class AddCandidateControllerServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter() ;
        
        HttpSession sess = request.getSession(); //After login this page should be accessed
        String userid = (String) sess.getAttribute("userid") ;
        if(userid == null)
        {
            sess.invalidate() ;
            response.sendRedirect("accessdenied.html");
            return; 
        }
        
        String candidate = (String)request.getParameter("id") ; 
        String usid = request.getParameter("uid" ) ; //When user will enter adhar no
        
      if(candidate != null && candidate.equals("getid"))
      {
          try{
                String id = CandidateDAO.getNewId() ;
                out.println(id);
                return ;
          
          }
          catch(SQLException ex)
          {
             RequestDispatcher rd = request.getRequestDispatcher("ShowException.jsp") ;
            
             request.setAttribute("Exception",ex);
             rd.forward(request, response) ;
          }
         
      }
      else if(usid != null)
      {
          try{
               String username = CandidateDAO.getUserNameById(usid);
               ArrayList<String> city = CandidateDAO.getCity() ;
               JSONObject json = new JSONObject();
               StringBuffer sb = new StringBuffer() ;
               for(String c : city)
               {
                   sb.append("<option value='"+c+"'>"+c+"</option>");
               }
               System.out.println(sb);
               if(username==null)
                   username="wrong";
               
               json.put("username",username);
               json.put("city",sb.toString());
               out.println(json);
          }
          catch(Exception ex)
          {
              RequestDispatcher rd = request.getRequestDispatcher("ShowException.jsp") ;
              request.setAttribute("Exception",ex);
              rd.forward(request, response) ;
          }
         
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
