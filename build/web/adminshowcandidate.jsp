<%-- 
    Document   : adminshowcandidate.jsp
    Created on : Jan 5, 2024, 10:37:56 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject" %>
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="java.util.ArrayList" %>
<%
     String userid = (String)session.getAttribute("userid") ;
     if(userid==null)
     {
        response.sendRedirect("accessdenied.html");
        return ; 
     }
     String result = (String)request.getAttribute("result");
     String isremove = (String)request.getAttribute("isremove") ; 
    
     StringBuffer displayBlock = new StringBuffer("");
     if(result != null && result.equalsIgnoreCase("candidateList"))
     {
        ArrayList<String> candidateId = (ArrayList<String>)request.getAttribute("candidateid");
        displayBlock.append("<option value='' >Choose ID</option>");
        for(String c : candidateId)
        {
           displayBlock.append("<option value='"+c+"'>"+c+"</option>");
        }
        
        JSONObject json = new JSONObject();
        json.put("cid",displayBlock.toString()); // toString() important hai nahi toh runtime pr error aayegi
        out.println(json);
      }
      
      else if(result != null && result.equalsIgnoreCase("details"))
      {
        CandidateDetails cd = (CandidateDetails)request.getAttribute("candidate");
//        String str = "<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px; height:200px;' />";
          String str = "<img src='data:image/jpg;base64,"+cd.getSymbol()+"' />";
//        displayBlock.append("<table>");
//        displayBlock.append("<tr><th>Candidate Name:  </th><td>"+cd.getCandidateName()+"</td></tr>");
//        displayBlock.append("<tr><th>Aadhar Id:  </th><td>"+cd.getUserId()+"</td></tr>"); 
//        displayBlock.append("<tr><th>City:  </th><td>"+cd.getCity()+"</td></tr>");
//        displayBlock.append("<tr><th>Party:  </th><td>"+cd.getParty()+"</td></tr>");
//        displayBlock.append("<tr><th>Symbol:  </th><td>"+str+"</td></tr>");
//        displayBlock.append("</table>");
   
        displayBlock.append("<div class='outer'>");
        displayBlock.append("<div class='imgdiv'>"+str+"</div>");
        displayBlock.append("<div class='detailsdiv'>");
        displayBlock.append("<div class='d1'> <span class='s1'>Candidate Name</span><span>"+cd.getCandidateName()+"</span></div>");
        displayBlock.append("<div class='d1'> <span class='s1'>Aadhar ID</span><span>"+cd.getUserId()+"</span></div>");
        displayBlock.append("<div class='d1'> <span class='s1'>Party</span><span>"+cd.getParty()+"</span></div>");
        displayBlock.append("<div class='d1'> <span class='s1'>City</span><span>"+cd.getCity()+"</span></div>");
        displayBlock.append("</div ></div>");
        if(isremove != null && isremove.equalsIgnoreCase("yes"))
        {
          displayBlock.append(" <input type='button' id='"+cd.getUserId()+"' onclick='delCandidate()' name='btn' value='Remove' class='delcandbtn'/>");
        }
       
        JSONObject json = new JSONObject();
        json.put("subdetails",displayBlock.toString());
        out.println(json);

      }
%>
