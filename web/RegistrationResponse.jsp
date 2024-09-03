<%-- 
    Document   : RegistrationResponse
    Created on : Sep 26, 2023, 9:24:57 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    boolean userfound=(boolean) request.getAttribute("userfound");
    boolean result =(boolean) request.getAttribute("result");
    String username = (String)request.getAttribute("username");
    if(userfound)
    out.println("uap");
    else if(result==true)
    out.println("success");
    else
    out.println("error");
    
%>
