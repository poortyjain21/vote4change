<%-- 
    Document   : updatecandidate
    Created on : Feb 17, 2024, 9:07:25?AM
    Author     : asus
--%>

<%@page import="org.json.JSONObject, java.util.ArrayList" %>
<%
   String userid = (String)session.getAttribute("userid");   
   if(userid == null)
   {
       session.invalidate() ;
       response.sendRedirect("accessdenied.html");
       return ; 
   }
   
   String cname = (String)request.getAttribute("cname");
   String adhar_no = (String)request.getAttribute("adhar_no") ;
   ArrayList<String> city = new ArrayList<>();
   city = (ArrayList<String>)request.getAttribute("city");
   StringBuffer displayBlock = new StringBuffer("");
   for(String c : city)
   {
     displayBlock.append("<option>"+c+"</option>");
   }
   
   JSONObject json = new JSONObject() ;
   json.put("cname",cname);
   json.put("adhar_no",adhar_no);
   json.put("city",displayBlock.toString());
   
   out.println(json);

%>
