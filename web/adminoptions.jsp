<%-- 
    Document   : adminoptions
    Created on : Jan 10, 2024, 5:12:54?PM
    Author     : asus
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet" >
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
         <script src="jspages/jquery.js"></script>
        <script src="jspages/adminoptions.js"></script>
        <title>Admin Options </title>
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
             displayBlock.append("<div class='container'>");
             displayBlock.append("<div id='dv1' onclick='redirectadministratorpage()'><img src='images/administrator.png' height='250px' width='250px'/><br/><h3>Admin Options</h3></div>");
             displayBlock.append("<div id='dv2' onclick='redirectvotingpage()'><img src='images/voteadmin.jpg' height='250px' width='250px'/><h3>Voting Page</h3></div>");
             displayBlock.append("<br><br><div align='center' id='result'></div>");
             out.println(displayBlock);
             

         %>
         
    </body>
</html>