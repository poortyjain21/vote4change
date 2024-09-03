<%-- 
    Document   : loginresponse
    Created on : Oct 21, 2023, 10:27:33 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String result =(String) request.getAttribute("result") ;
    String userid = (String) request.getAttribute("userid") ;
        
    if(userid != null && result != null)
    {
        // session object created! 
        HttpSession sess = request.getSession();
        sess.setAttribute("userid",userid) ;
        String url = "";
        
        if(result.equalsIgnoreCase("Admin"))
        {
           url="AdminControllerServlet;jsessionid="+sess.getId();
        }
        else
        {
           url = "VotingControllerServlet;jsessionid="+sess.getId();
        }
        
        out.println(url); //Sending response to Ajax
    }
    else{
           out.println("error");
        }
    %>
