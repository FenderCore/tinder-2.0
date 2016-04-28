<%-- 
    Document   : status
    Created on : 26/04/2016, 3:44:03 PM
    Author     : Shannon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="status">
<%
    
    String logged = (String)request.getSession().getAttribute("logged");
    if(logged == null)
        logged = "false";
    String username = "";
    if(logged.equals("true"))
    {
        username = (String)request.getSession().getAttribute( "username" );
        Integer id = (Integer)request.getSession().getAttribute( "id" );
        out.print("Welcome <a style='color:blue' href='Account'>" + username + "</a> <a style='color:blue' href='Logout'>Logout</a>");
    } else {
        out.print("<a style='color:blue' href='login.jsp'>Login</a> <a style='color:blue' href='register.jsp'>Register</a>");
    }
    
%>

</div>
