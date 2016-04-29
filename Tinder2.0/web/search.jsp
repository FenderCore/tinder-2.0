<%-- 
    Document   : search
    Created on : 26/04/2016, 12:52:13 PM
    Author     : Shannon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tinder 2.0 - Search</title>
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
            <form action="Search" method="get">
                Name: <input type="text" name="name"/> <br>
                Age from:<input type="number" name="age1" min="18" max="125" value="18"/> to <input type="number" name="age2" min="18" max="125" value="35"/><br>
                Sex <select name="sex"><option>Male</option><option>Female</option><option>Both</option></select> <br>
                <input type="submit"  value="Search"/>
            </form>
        </div>
    </body>
</html>
