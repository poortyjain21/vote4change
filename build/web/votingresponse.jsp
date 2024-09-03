<%-- 
    Document   : votingresponse
    Created on : Jan 14, 2024, 1:20:26 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dto.CandidateInfo" %>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jspages/vote.js"></script>
        <script src="jspages/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet" >
        <title>Voting Response </title>
        <style>
               .cout {
         
        margin:0px 35%;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 15px;
        width: 30%;
        background: rgb(248, 248, 248);
        border-radius:10px;
/*        height:500px;*/
        
      }
      .cin1 {
        margin-top: 25px;
        height: 300px;
        width: 400px;
        
      }

      img {
        height: 100%;
        width: 100%;
        border-radius:10px;
      }
      .cin2 {
        margin: 40px 0px;
        display: flex;
        flex-direction: column;
      
         width: 300px;
         align-items: center; 
      }
      .d1,
      .d2,
      .d3 {
        letter-spacing: 1.02px;
        font-size: 18px;
        margin-top: 12px;
        width:100%;
       color:black;
        display: flex;
        justify-content: space-between;
      }
        </style>
    </head>
    <body>
    <%
           String userid = (String)session.getAttribute("userid") ;
            if(userid==null)
            {
               response.sendRedirect("accessdenied.html");
               return ; 
            } 
            CandidateInfo c = (CandidateInfo)session.getAttribute("candidate");
            StringBuffer displayBlock = new StringBuffer(""); 
            displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>");
            if(c == null)
            {
              displayBlock.append("<div class='subcandidate'> Sorry!  Your vote could not be casted </div><br><br>");
              displayBlock.append("<div><h4 id='logout><a href='LoginControllerServlet?logout=logout'>Logout</a></h4></div>");
              out.println(displayBlock);
            }
            else
            {
              displayBlock.append("<div class='subcandidate'>ThankYou for voting!</div><br><br>");
              displayBlock.append("<br><div class='candidateprofile'>Your vote added successfully!</div><br><br>");
//              displayBlock.append("<img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px; height:200px'/>");
//              displayBlock.append("<br><br><div class='candidateprofile'>Candidate Name : "+c.getCandidateName()+"<br>"); 
//              displayBlock.append("Candidate Id : "+c.getCandidateId()+"<br>");
//              displayBlock.append("Party : "+c.getParty()+"<br></div>");
 
//             StringBuffer displayBlock = new StringBuffer("<div class='cout'>");
          displayBlock.append("<div class='cout'>");
          displayBlock.append("<div class='cin1'><img src='data:image/jpg;base64,"+c.getSymbol()+"'/></div>");
          displayBlock.append("<div class='cin2'>");
          displayBlock.append("<div class='d1'><span>Candidate Name</span><span>"+c.getCandidateName()+"</span></div>");
          displayBlock.append("<div class='d2'><span>Candidate ID</span><span>"+c.getCandidateId()+"</span></div>");
          displayBlock.append("<div class='d3'><span>Party</span><span>"+c.getParty()+"</span></div>");
          displayBlock.append("</div></div>");
             
              out.println(displayBlock);
    
            }
            
        
    %>
    </body>    
</html>
