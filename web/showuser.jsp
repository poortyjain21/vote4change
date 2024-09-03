<%-- 
    Document   : showuser
    Created on : Jan 20, 2024, 7:27:59 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, evoting.dto.UserInfo"%>
<head>
   <link href="stylesheet/result.css" rel="stylesheet">
</head>
<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return ; 
    }
    
    ArrayList<UserInfo> user = (ArrayList<UserInfo>)request.getAttribute("userdetails");
    StringBuffer displayBlock = new StringBuffer("<table align='center' class='table-striped' style='transition=300ms'>");
    displayBlock.append("<tr><th>UserName</th><th>Aadhar no</th><th>City</th><th>Email</th></tr>");
    for(UserInfo u : user)
    {
      displayBlock.append("<tr><td>"+u.getUsername()+"</td><td>"+u.getUserid()+"</td><td>"+u.getCity()+"</td><td>"+u.getEmail()+"</td></tr>");
    }
    displayBlock.append("</table>");
    out.println(displayBlock);

%>