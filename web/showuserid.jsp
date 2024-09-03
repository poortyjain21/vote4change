<%-- 
    Document   : showuserid
    Created on : Jan 26, 2024, 8:32:23?PM
    Author     : asus
--%>
<%@page import="java.util.ArrayList,org.json.JSONObject"  %>

<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null)
    {
       session.invalidate();
       response.sendRedirect("accessdenied.html");
       return ; 
    }
    String result = (String)request.getAttribute("result");
    if(result != null && result.equalsIgnoreCase("id"))
    {
      ArrayList<String> userList =(ArrayList<String>)request.getAttribute("uid");
      StringBuffer displayBlock = new StringBuffer("");
      displayBlock.append("<option value='' >Choose ID</option>");
      for(String x : userList)
     {
        displayBlock.append("<option value='"+x+"'>"+x+"</option>");
     }
     JSONObject json = new JSONObject();
     json.put("usid",displayBlock.toString()); // toString() important hai nahi toh runtime pr error aayegi
     out.println(json);

    }
    else{
          boolean ans = (boolean)request.getAttribute("ans");
          if(ans==true)
          out.println("success");
          else
          out.println("failed");
    }
    

%>