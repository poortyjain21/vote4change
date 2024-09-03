<%-- 
    Document   : verifyvote
    Created on : Jan 14, 2024, 1:14:36?PM
    Author     : asus
--%>

<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null)
    {
       session.invalidate();
       response.sendRedirect("accessdenied.html");
       return ; 
    }
    boolean result = (boolean)request.getAttribute("result");
    
    if(result == true)
       out.println("success");
    else
       out.println("failed");
    
%>
