<%-- 
    Document   : editprofile
    Created on : 26/04/2016, 12:52:19 PM
    Author     : Shannon
--%>

<%@page import="tinder.Profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tinder 2.0 - Edit Profile</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <div id="container">
        <div id="header"><img src="images/logo.png" width=1024></div>
        <jsp:include page="navigation.html" />
        <jsp:include page="status.jsp" />
        <div id="main">
            
        <% 
            String logged = (String)session.getAttribute("logged");
            Profile account = (Profile)session.getAttribute("account");
            String username = (String)session.getAttribute("username");
            if(logged == null)
                logged = "false";
            if(!logged.equals("true"))
            {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "login.jsp"); 
            } else {
                 if(account == null)
                 {
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", "Logout?timeout=1"); 
                 } else {
                
            out.print("<form name='form' action='EditProfile' method='post'> " +
                "Name: <input type='text' name='name' value='" + account.getName() + "'/> <br>" +
                "Age: <input type='number' name='age' min='18' max='125' value='" + account.getAge() + "'/> <br>" +
                "Sex: <select name='sex' value='Female'><option>Male</option><option>Female</option></select> <br>" +
                "Enter your password: <input type='password' name='password' /> <br>" +
                "<input type='submit' value='Edit Profile'/> " +
            "</form> ");
            
            out.print(" <script>    var element = document.forms['form']['sex']; " +
                        " element.value = '" + account.getSex() + "'; </script>");
                 }
            } 
        %>
        </div>
    </body>
</html>
