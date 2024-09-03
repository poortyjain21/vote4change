<%-- 
    Document   : showcandidate
    Created on : Jan 8, 2024, 11:38:17 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, evoting.dto.CandidateInfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jspages/vote.js"></script>
        <script src="jspages/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet" >
        <title>Show candidate </title>
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
         displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"
         +"<div class='subcandidate'>WHOM DO YOU WANT TO VOTE</div>"  
         +"<div class='logout'><a href='login.html'>logout</a></div>"
         +"</div><div class='buttons'>");
         
         ArrayList<CandidateInfo> candidateList = (ArrayList<CandidateInfo>)request.getAttribute("candidateList");
         for(CandidateInfo c : candidateList)
         {
            displayBlock.append("<input type='radio' name='flat' id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' onclick='addvote()'");  
            displayBlock.append("<label for='"+c.getCandidateId()+"'><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px; height:200px'/></label>");
            displayBlock.append("<br><div class='candidateprofile'><p>CandidateId: "+c.getCandidateId()+"<br>");
            displayBlock.append("CandidateName: "+c.getCandidateName()+"<br>");
            displayBlock.append("Party: "+c.getParty()+"<br></p></div>");
      
         }
         displayBlock.append("</div>");
//         // If html is not displayed then the cross check the closing of the tags
         out.println(displayBlock);
       
      %>
        
        
    </body>
</html>