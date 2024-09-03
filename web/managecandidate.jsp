<%-- 
    Document   : managecandidate
    Created on : Nov 9, 2023, 12:44:04 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="jspages/adminoptions.js"></script>
        <script src="jspages/jquery.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet" >
        <title>manage candidate </title>
    </head>
    <body>
        <%
            String userid = (String)session.getAttribute("userid") ;
            if(userid==null)
            {
            
               response.sendRedirect("accessdenied.html");
               return ; 
             }
             
            StringBuffer displayBlock = new StringBuffer("");
            displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+"<div class='subcandidate'>ADMIN ACTIONS PAGE</div><br><br>");
            displayBlock.append("<div class='logout'><a href='login.html'>logout</a></div></div>");
            displayBlock.append("<div class='container'>");
            displayBlock.append("<div id='div1' onclick='showaddcandidateform()'>"
            + "<img src='images/addcandidate.png' height='250px' width='250px'>"
            + "<br><h3>Add Candidate</h3></div>"       
            );
            
           displayBlock.append("<div id='dv2' onclick='showupdatecandidateform()'>"
           + "<img src='images/update1.jpg ' height='250px' width='250px'>"
           +"<br><h3>Update Candidate</h3></div>"        
           
           );
          
           displayBlock.append("<div id='dv3' onclick='showCandidate()'>"
           + "<img src='images/showcandidate.jpeg' height='250px' width='250px'>"
           +"<br><h3>Show Candidate</h3></div>"       
           );         

          displayBlock.append("<div id='dv4' onclick='deletecandidate()'>"
           +"<img src='images/update3.jpg' height='250px' width='250px'>"
          +"<br><h3>Delete Candidate</h3></div>"
          );

           displayBlock.append("</div >");
           displayBlock.append("<br><br><div align='center' id='result'></div>");
           out.println(displayBlock);

            
        %>
    </body>
    
</html>
