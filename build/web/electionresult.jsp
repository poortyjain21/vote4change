<%-- 
    Document   : electionresult
    Created on : Jan 16, 2024, 6:55:27 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dto.CandidateDetails,java.util.* " %>
<head>
   <link href="stylesheet/result.css" rel="stylesheet">
</head>

    <%
         String userid = (String)session.getAttribute("userid") ;
          if(userid==null)
          {
            response.sendRedirect("accessdenied.html");
            return ; 
          }
       int totalVoteCount = (int)request.getAttribute("votecount");  
       LinkedHashMap<CandidateDetails,Integer> result = (LinkedHashMap<CandidateDetails,Integer>)request.getAttribute("result");
       Set s = result.entrySet();
       Iterator it = s.iterator() ;
       StringBuffer displayBlock = new StringBuffer("");
       displayBlock.append("<table class='table-striped'>");
       displayBlock.append("<tr><th>Candidate Id</th><th>Candidate Name</th><th>Party</th><th>Symbol</th><th>City</th><th>Vote Count</th><th>Vote %</th></tr>");
       while(it.hasNext())
       {
          Map.Entry<CandidateDetails,Integer> e = (Map.Entry)it.next();
          CandidateDetails cd = e.getKey() ;
          float voteper = (e.getValue()*100.0f)/totalVoteCount;
          displayBlock.append("<tr><td>"+cd.getCandidateId()+"</td><td>"+cd.getCandidateName()+"</td><td>"+cd.getParty()+"</td><td><img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px; height:200px;'/></td><td>"+cd.getCity()+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");
       }
       displayBlock.append("</table>");
      
       out.println(displayBlock);
    %>    
            
