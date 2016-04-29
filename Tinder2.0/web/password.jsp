<%-- 
    Document   : password
    Created on : 26/04/2016, 12:52:19 PM
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
        <div id="header"><img src="images/logo.png" width=1024></div>
        <% 
            String logged = (String)session.getAttribute("logged");
            int user1 = 0;
            if(logged == null)
            {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "login.jsp"); 
            }
        %>
        <jsp:include page="navigation.html" />
        <jsp:include page="status.jsp" />
        <div id="main">
            <form name="passForm" action="ChangePassword" onSubmit="return validate()" method="post">
                New Password <input type="password" name="password"/> <br>
                Re-enter Password <input type="password" name="reenter"/> <br>
                Old Password <input type="password" name="oldPassword"/> <br>
                <input type="submit" value="Change Password"/>
            </form>
            <script>
                function validate() 
                {
                    var x = document.forms["passForm"]["password"].value;
                    var y = document.forms["passForm"]["reenter"].value;
                    //Check that it isn't null or empty
                    if (x != y) {
                            alert("Passwords must match");
                            return false;
                    }
                    return true;
                }
            </script>
        </div>
    </body>
</html>
