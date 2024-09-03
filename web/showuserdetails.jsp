<%-- 
    Document   : showuserdetails
    Created on : Feb 17, 2024, 5:15:18?PM
    Author     : asus
--%>

<%@page import="java.util.ArrayList" %>
<head><style>
    table {
        border-collapse: collapse;
        width: 50%;
        margin-top: 20px; /* Add margin to the top for spacing */
    }

    th, td {
        padding: 8px;
        text-align: left;
        border: 1px solid #ddd;
        width:20%;
    }

    th {
        background-color: #f2f2f2;
        color:black ; 
/*        background  : #e5e5e5;*/
    }

    tr:hover {
        background-color: #f5f5f5;
        font : black ; 
    }

    input[type="button"] {
        background-color: #ff3333;
        color: #fff;
        border: none;
        padding: 8px 12px;
        cursor: pointer;
        margin-top : 20px;
        border-radius : 5px;
        
    }
    td:hover{
        color : black;
    }
    
    .hello{
        border:none ; 
    }
   
</style>

</head>
<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return ; 
    }
    
    ArrayList<String> details = (ArrayList<String>)request.getAttribute("details");
    StringBuffer displayBlock = new StringBuffer("<table>");
    
    displayBlock.append("<tr><th>UserName</th><td>"+details.get(1)+"</td><tr>");
    displayBlock.append("<tr><th>AadharID</th><td>"+details.get(0)+"</td><tr>");
    displayBlock.append("<tr><th>Address</th><td>"+details.get(2)+"</td><tr>");
    displayBlock.append("<tr><th>Mobile</th><td>"+details.get(5)+"</td><tr>");
    displayBlock.append("<tr><th>City</th><td>"+details.get(3)+"</td><tr>");
    displayBlock.append("<tr><th>Email</th><td>"+details.get(4)+"</td><tr>");
//    displayBlock.append("<tr class='hello'><td><input type='button' id='"+details.get(0)+"' onclick='delUser()' name='btn' value='Remove'/></td></tr>");
    displayBlock.append("</table>");
     displayBlock.append(" <input type='button' id='"+details.get(0)+"' onclick='delUser()' name='btn' value='Remove'/>");
    out.println(displayBlock);


%>