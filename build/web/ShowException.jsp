<%-- 
    Document   : ShowException
    Created on : Sep 26, 2023, 9:37:45 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   Exception ex = (Exception)request.getAttribute("Exception");
   out.println("Some Exception Occured in Show Exception JSP"+ex.getMessage());
%>
