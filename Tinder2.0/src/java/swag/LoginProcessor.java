/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swag;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tinder.Profile;

/**
 *
 * @author Shannon
 */
public class LoginProcessor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   private final char QUOTE = '"';
   
    Connection conn;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        HttpSession session = request.getSession(true);
        // obtain the values of the form data automatically URL decoded
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String url= "jdbc:mysql://localhost:3306/tinder";
        String usernameDB = "root";
        String passwordDB = "";
        
        String message = "";
        String sql = "";
        boolean success = false;
        
        PrintWriter pw = response.getWriter();
        try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(url, usernameDB, passwordDB);
        } catch (Exception e) {
              //e.printStackTrace(pw);
              message = e.getMessage();
          }
        ResultSet rs;
        
        try {
            Statement stmt = conn.createStatement();
            sql = "SELECT * FROM account WHERE LOWER(username) = LOWER('" + username + "')";
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                String pass = rs.getString("password");
                if(pass.equals(password))
                {
                    success = true;
                    message = "Successfully logged in";
                    session.setAttribute("logged", "true");
                    session.setAttribute("username", rs.getString("username"));
                    session.setAttribute("id", rs.getInt("account_id"));
                    
                    session.setAttribute("account", new Profile(rs.getString("full_name"), rs.getInt("age"), rs.getString("sex"), rs.getInt("account_id")));
                } else {
                    message = "Incorrect Password";
                }
            } else {
                message = "Username does not exist";
            }
        } catch (Exception e) {
            //message = e.getMessage();
            //e.printStackTrace(pw);
        }
        
      // set response headers before returning any message content
      response.setContentType("text/html");
      // prepare the content of the response
      pw.println("<!DOCTYPE HTML PUBLIC " + QUOTE +
        "-//W3C//DTD HTML 4.0 Transitional//EN" + QUOTE + ">\n" +
        "<HTML>\n" + "<HEAD>\n" +
        "<TITLE>Tinder 2.0 - Register</TITLE>\n" + "</HEAD>\n" + "<BODY>\n" +
        "<link rel='stylesheet' type='text/css' href='style.css'>" +
        "<div id='container'>" +
        "<div id='header'> <img src='images/logo.jpg' width=1024></div>");
        //"<jsp:include page='/navigation.html' />" +
        //"<jsp:include page='/status.jsp' />" 
        request.getRequestDispatcher("/navigation.html").include(request, response);
        request.getRequestDispatcher("/status.jsp").include(request, response);
        pw.println("<div id='main'>" +
        "<P>"+ message + "</p>" +
        "</div>" +
        "</BODY>\n</HTML>\n");
      pw.close();
   }
   
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {  
       doGet(request, response);
   }

   // filter string so that it doesn't contain special HTML characters
   public static String filter(String text)
   {  StringBuffer buffer = new StringBuffer();
      for (int i=0; i<text.length(); i++)
      {  char c = text.charAt(i);
         if (c == '<') buffer.append("&lt;");
         else if (c == '>') buffer.append("&gt;");
         else if (c == '"') buffer.append("quot;");
         else if (c == '&') buffer.append("amp;");
         else buffer.append(c);
      }
      return buffer.toString();
   }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
