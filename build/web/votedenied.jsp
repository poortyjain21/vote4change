<%-- 
    Document   : votedenied
    Created on : Jan 20, 2024, 12:11:14?PM
    Author     : asus
--%>

<!DOCTYPE html>


<%@page import="evoting.dto.CandidateInfo" %>
<html>
    <head>
        <title>Vote Denied</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
       
        
        <style>
              div
              {
                   color: white;
                   font-size: 20px;
                   font-weight: bold;
              }
              div.logout
              {
                position:fixed;
                left: 90%;
                 top:  5.5%;
               }
              
/*                 body {
                       display: flex;
                       flex-direction: column;
                       align-items: center;
                       background-color: rgb(106, 65, 145);
                       color: black;;
                      }*/
      .cout {
          
/*         margin-top:35px; */
/*        margin-left:35%;*/
        margin:0px 35%;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 15px;
        width: 30%;
/*        background-color: rgb(191, 148, 228);*/
        background: rgb(248, 248, 248);
        border-radius:10px;
        
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
    <body><br>
        <div class="candidate">VOTE FOR CHANGE</div>
        <br>
        <div class="subcandidate">Vote Denied</div>
        <div class='logout'><a href='login.html'>logout</a></div>
        <div  id="denied" align="center">
        <h1>Already Voted!</h1> 
        <h4>You Voted For...</h4><br>

        </div>
        <%
            String userid = (String)session.getAttribute("userid") ;
            if(userid==null)
           {
             response.sendRedirect("accessdenied.html");
             return ; 
           }
           CandidateInfo cd = (CandidateInfo)request.getAttribute("candidate");
          String str = "<img src='data:image/jpg;base64,"+cd.getSymbol()+"'/>";
          
//          StringBuffer displayBlock = new StringBuffer("<div align='center'>");
//          displayBlock.append("<table border='3px solid #1B2631'>");
//          displayBlock.append("<tr><th>Candidate Name :  </th><td>"+cd.getCandidateName()+"</td></tr>");
//          displayBlock.append("<tr><th>Candidate Id :  </th><td>"+cd.getCandidateId()+"</td></tr>"); 
//          displayBlock.append("<tr><th>Party :  </th><td>"+cd.getParty()+"</td></tr>");
//            displayBlock.append("<tr><th>Symbol :  </th><td>"+str+"</td></tr>");
//          displayBlock.append("</table>");
//          displayBlock.append("</div>");


          StringBuffer displayBlock = new StringBuffer("<div class='cout'>");
          displayBlock.append("<div class='cin1'>"+str+"</div>");
          displayBlock.append("<div class='cin2'>");
          displayBlock.append("<div class='d1'><span>Candidate Name</span><span>"+cd.getCandidateName()+"</span></div>");
          displayBlock.append("<div class='d2'><span>Candidate ID</span><span>"+cd.getCandidateId()+"</span></div>");
          displayBlock.append("<div class='d3'><span>Party</span><span>"+cd.getParty()+"</span></div>");
          displayBlock.append("</div></div>");
          
          out.println(displayBlock);
            
        %>
    </body>
</html>

