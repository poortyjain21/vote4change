/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.UpdateCandidateDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author asus
 */
public class UpdateDetailsCandidateControllerServlet extends HttpServlet {

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
            try{
                DiskFileItemFactory df = new DiskFileItemFactory();
                ServletFileUpload sfu = new ServletFileUpload(df);
                ServletRequestContext srq = new ServletRequestContext(request);
                List<FileItem> multiList = sfu.parseRequest(srq);
                ArrayList<String> objValues = new ArrayList<>() ;
                InputStream inp = null;
                
               for(FileItem fit : multiList)
               {
                   if(fit.isFormField())
                   {
                       String fname = fit.getFieldName();
                       String value = fit.getString();
                       System.out.println("Inside if");
                       System.out.println(fname+" : "+value);
                       objValues.add(value);
                   }
                   else{
                         inp = fit.getInputStream();
                         String key = fit.getFieldName();
                         String fileName = fit.getName();
                         System.out.println("Inside else");
                         System.out.println(key+" : "+fileName);
                   }
               }
               System.out.println("INSIDE UPDATE DETAILS CANDIDATE SERVLET" + objValues);
               UpdateCandidateDTO obj = new  UpdateCandidateDTO(objValues.get(0), objValues.get(1),objValues.get(2),inp);
               boolean result = CandidateDAO.updateCandidate(obj);
                System.out.println("is Updated "+ result);
            }
            catch(Exception ex)
            {
                System.out.println("Exception in AddNewCandidateServlet");
                ex.printStackTrace();
            }
            finally{
                
                if(rd!=null)
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
