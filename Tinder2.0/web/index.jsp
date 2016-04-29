<%-- 
    Document   : newjsp
    Created on : 26/04/2016, 12:31:16 PM
    Author     : Shannon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tinder 2.0 - Home</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="container">
        <div id="header"><img id="headerimage" src="images/logo.png" width=1024></div>
        <jsp:include page="navigation.html" />
        <jsp:include page="status.jsp" />
        <div id="main">
            Welcome to Tinder 2.0 <br>
            The greatest AUT dating site you will ever use!
            
            <div style="margin-right: 50px;"><img src="images/ugly.png" style="float: right; animation: blink 2s; animation-iteration-count: infinite;"/></div>
            <br>&nbsp;&nbsp;<div><img src="images/derm.jpg" style=""/></div>
            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/pc.png" style=""/>
            <img src="images/queen.jpg" style="float: right;"/>
        </div>
    </body>
</html>
