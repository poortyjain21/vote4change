<%-- 
    Document   : resultoptions
    Created on : Jan 20, 2024, 4:31:47 PM
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
        <title>Result Options </title>
    </head>
    <body>
     <%
            
             StringBuffer displayBlock = new StringBuffer("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br/>"+"<div class='subcandidate'>Admin Options Page</div><br/><br/>"+"<div class='logout'><a href='login.html'>logout</a></div></div>");
             displayBlock.append("<div class='container'>");
             displayBlock.append("<div id='dv1' onclick='candidateresults()'><img src='images/administrator.png' height='300px' width='300px'/><br/><h3>Candidate-wise Results</h3></div>");
             displayBlock.append("<div id='dv2' onclick='partyresults()'><img src='images/voteadmin.jpg' height='300px' width='300px'/><h3>Party-wise Results</h3></div>");
             displayBlock.append("<br><br><div align='center' id='result'></div>");
             out.println(displayBlock);
         
         
      %>
    </body>
</html>
