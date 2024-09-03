<%-- 
    Document   : removecandidate
    Created on : Jan 19, 2024, 8:27:18?PM
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
    boolean result = (boolean)request.getAttribute("result") ; 
    if(result == true)
    out.println("success");
    else
    out.println("failed") ;     

%>