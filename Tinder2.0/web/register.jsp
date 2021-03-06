<%-- 
    Document   : register
    Created on : 26/04/2016, 12:52:19 PM
    Author     : Shannon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tinder 2.0 - Register</title>
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
            <form action="RegisterProcessor" method="post">
                Username: <input type="username" name="username"/> <br>
                Password: <input type="password" name="password"/> <br>
                Name: <input type="text" name="name"/> <br>
                Age <input type="number" name="age" min="18" max="125"/> <br>
                Sex <select name="sex"><option>Male</option><option>Female</option></select> <br>
                <input type="submit" value="Register"/>
            </form>
        </div>
    </body>
</html>
