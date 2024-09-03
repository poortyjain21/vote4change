<%-- 
    Document   : adminactions
    Created on : Jan 10, 2024, 6:22:26 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jspages/adminoptions.js"></script>
        <script src="jspages/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet" >
        <title>Admin Actions</title>
</head>
<body>
    <%
            String userid = (String)session.getAttribute("userid") ;
            if(userid==null)
            {
               response.sendRedirect("accessdenied.html");
               return ; 
            }
            StringBuffer displayBlock = new StringBuffer("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br/>"+"<div class='subcandidate'>Admin Actions Page</div><br/><br/>"+"<div class='logout'><a href='LoginControllerServlet?logout=yes' >logout</a></div></div>");
             displayBlock.append("<div class='container'>");
             displayBlock.append("<div id='dv1' onclick='manageuser()'style= 'margin: 0 4px'><img src='images/muser.png' height='250px' width='250px'/><br/><h3>Manage Users</h3></div>");
             displayBlock.append("<div id='dv2' onclick='managecandidate()'><img src='images/ManageCandLists.jpg' height='250px' width='250px'/><h3>Manage Candidates</h3></div>");
             displayBlock.append("<div id='dv2' onclick='electionresult()'><img src='images/resultgraph.jpg' height='250px' width='250px'/><h3>Voting Result</h3></div></div>");
             displayBlock.append("<br><br><div align='center' id='result'></div>");
             out.println(displayBlock);
             
        
        
    %>
    
    
</body>    

</html>