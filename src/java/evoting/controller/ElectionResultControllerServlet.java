/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dao.VoteDAO;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
public class ElectionResultControllerServlet extends HttpServlet {

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
        String userid = (String) sess.getAttribute("userid") ;
        if(userid == null)
        {
            sess.invalidate() ;
            response.sendRedirect("accessdenied.html");
            return; 
        }
        try{
           Map<String,Integer> result = VoteDAO.getResult();
           Set s = result.entrySet();
           Iterator it = s.iterator() ;
           LinkedHashMap<CandidateDetails,Integer> resultDetails = new LinkedHashMap<>() ;
           while(it.hasNext())
           {
             Map.Entry<String,Integer> e = (Map.Entry)it.next();
             CandidateDetails cd = CandidateDAO.getDetailsById(e.getKey());
             resultDetails.put(cd , e.getValue()) ;
           }
           request.setAttribute("votecount",VoteDAO.getVoteCount());
           request.setAttribute("result",resultDetails);
            System.out.println("vote count = "+VoteDAO.getVoteCount());
            System.out.println(resultDetails);
           rd = request.getRequestDispatcher("electionresult.jsp");
        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("Exception",ex);
            rd = request.getRequestDispatcher("ShowException.jsp");
        }
        finally{
            System.out.println("Inside ElectionResultControllerServlet");
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
