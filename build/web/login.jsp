<%-- 
    Document   : login
    Created on : 26/04/2016, 3:50:07 PM
    Author     : Shannon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tinder 2.0</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="container">
        <div id="header"><img src="images/logo.jpg" width=1024></div>
        <jsp:include page="navigation.html" />
        <jsp:include page="status.jsp" />
        <div id="main">
            <form action="LoginProcessor" method="post">
                Username: <input type="username" name="username" /> <br>
                Password: <input type="password" name="password" /> <br>
                <input type="submit" />
            </form>
        </div>
    </body>
</html>