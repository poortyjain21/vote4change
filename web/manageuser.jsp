<%-- 
    Document   : manageuser
    Created on : Jan 20, 2024, 5:21:57 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet" >
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
         <script src="jspages/jquery.js"></script>
        <script src="jspages/adminoptions.js"></script>
        <title>Manage Users </title>
    </head>
    <body>
        <%
            String userid = (String)session.getAttribute("userid") ;
           if(userid==null)
           {
            response.sendRedirect("accessdenied.html");
            return ; 
           }
          StringBuffer displayBlock = new StringBuffer("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br/>"+"<div class='subcandidate'>Admin Options Page</div><br/><br/>"+"<div class='logout'><a href='login.html'>logout</a></div></div>");
          displayBlock.append("<div class='container' class='manageuser'>");
          displayBlock.append("<div id='dv1' onclick='showUser()'><img src='images/showusers.png' height='300px' width='300px'/><br/><h3>Show Users</h3></div>");
          displayBlock.append("<div id='dv2' onclick='removeUser()'><img src='images/delete.jpg' height='300px' width='300px' ><h3>Remove User</h3></div></div>");
          displayBlock.append("<br><br><div align='center' id='result' ></div>");
          out.println(displayBlock);        
        
        %>
    </body>
</html>
