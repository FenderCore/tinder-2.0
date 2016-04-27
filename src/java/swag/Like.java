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
import java.util.ArrayList;
import java.util.Iterator;
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
public class Like extends HttpServlet {

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
        
        
        HttpSession session = request.getSession();
        
        
        String logged = (String)session.getAttribute("logged");
        if(logged == null)
            logged = "false";
        if(!logged.equals("true"))
        {
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "login.jsp?"); 
        }
        
        // obtain the values of the form data automatically URL decoded
        String user2 = request.getParameter("id");
        String user1 = (String)session.getAttribute("id");
        
        String url= "jdbc:mysql://localhost:3306/tinder";
        String usernameDB = "root";
        String passwordDB = "";
        String message = "";
        String sql = "";
        
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
            sql = "INSERT INTO swipe (user1, user2, liked)" 
               + " VALUES ('" + user1 + "', '" + user2 + "', '" + 1 + "')";
            stmt.executeUpdate(sql);
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
        pw.println("<div id='main'>" );
        pw.print("Profile liked!");
        
        pw.println(
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