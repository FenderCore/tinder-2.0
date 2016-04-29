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
import ejb.Profile;

/**
 *
 * @author Shannon
 */
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        int user1 = 0;
        if(logged == null)
            logged = "false";
        else
            user1 = (Integer)session.getAttribute("id");
            
        // obtain the values of the form data automatically URL decoded
        String name = request.getParameter("name");
        String age1 = request.getParameter("age1");
        String age2 = request.getParameter("age2");
        String sex = request.getParameter("sex");
        
        if(name == null) name = "";
        if(age1 == null) age1 = "";
        if(age2 == null) age2 = "";
        if(sex == null) sex = "";

        String url= "jdbc:mysql://localhost:3306/tinder";
        String usernameDB = "root";
        String passwordDB = "";
        
        int results = 0;
        
        String message = "Number of results: ";
        String sql = "";
        boolean success = false;
        ArrayList<Profile> profiles = new ArrayList<>();
        
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
            sql = "SELECT * FROM account WHERE full_name like '%" + name + "%' AND age between " + age1 + " AND " + age2;
            if(!sex.equals("Both"))
                sql += " AND sex like '" + sex +"'";
            sql += " ORDER BY full_name";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                results++;
                String result_name = rs.getString("full_name");
                int result_age = rs.getInt("age");
                String result_sex = rs.getString("sex");
                int result_id = rs.getInt("account_id");
                if(!logged.equals("true"))
                    profiles.add(new Profile(result_name, result_age, result_sex, result_id));
                else {
                     try {
                        Statement stmt2 = conn.createStatement();
                        sql = "SELECT * FROM swipe WHERE user1_id = " + user1 + " AND user2_id = " +  result_id;
                        ResultSet rs2 = stmt2.executeQuery(sql);
                        if(rs2.next())
                        {
                            results--;
                        } else {
                            profiles.add(new Profile(result_name, result_age, result_sex, result_id));
                        }
                    } catch (Exception e) {
                        message = e.getMessage();
                        e.printStackTrace(pw);
                    }
                }
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
        "<TITLE>Tinder 2.0 - Search Results</TITLE>\n" + "</HEAD>\n" + "<BODY>\n" +
        "<link rel='stylesheet' type='text/css' href='style.css'>" +
        "<div id='container'>" +
        "<div id='header'> <img src='images/logo.png' width=1024></div>");
        //"<jsp:include page='/navigation.html' />" +
        //"<jsp:include page='/status.jsp' />" 
        request.getRequestDispatcher("/navigation.html").include(request, response);
        request.getRequestDispatcher("/status.jsp").include(request, response);
        pw.println("<div id='main'>" );
        pw.print(" <form action='Search' method='get'>" +
                "Name: <input type='text' name='name' value='" + name + "'/> <br>" +
                "Age from:<input type='number' name='age1' min='18' max='125' value='" + age1 + "'/> to <input type='number' name='age2' min='18' max='125' value='" + age2 + "'/><br>");
        pw.print("Sex <select name='sex'><option ");
        if(sex.equals("Male")) pw.print("selected");
        pw.print(">Male</option><option ");
        if(sex.equals("Female")) pw.print("selected");
        pw.print(">Female</option><option ");
        if(sex.equals("Both")) pw.print("selected");
        pw.print(">Both</option></select> <br>" );
        pw.print("<input type='submit'  value='Search'/>" +
            "</form>");
        
        pw.println("<P>"+ message +  results + "</p>");
        pw.println("<table style='width: 800px; margin: auto'>");
        Iterator<Profile> iter = profiles.iterator();
        while(iter.hasNext())
        {
            Profile profile1 = iter.next();
            Profile profile2 = null;
            Profile profile3 = null;
            if(iter.hasNext())
                profile2 = iter.next();
            if(iter.hasNext())
                profile3 = iter.next();
            
            pw.println("<tr align='center'>");
            pw.println("<td><img align='bottom' src='images/" + profile1.getSex() + (profile1.getAge() % 10) + ".jpg' width=200><br>");
            pw.println(profile1.getName() + ", " + profile1.getAge()+ "</td><td></td>");
            if(profile2 != null)
            {
                pw.println("<td><img align='bottom' src='images/" + profile2.getSex() + (profile2.getAge() % 10) + ".jpg' width=200><br>");
                pw.println(profile2.getName() + ", " + profile2.getAge()+ "</td><td></td>");
            }
            if(profile3 != null)
            {
                pw.println("<td><img align='bottom' src='images/" + profile3.getSex() + (profile3.getAge() % 10) + ".jpg' width=200><br>");
                pw.println(profile3.getName() + ", " + profile3.getAge()+ "</td>");
            }
            pw.println("</tr>");
            
            pw.println("<tr>");
            pw.println("<td align='center' ><a href='Like?id=" + profile1.getId() + "' ><img align='bottom' src='images/like.png' width=50></a>");
            pw.println("<a href='Dislike?id=" + profile1.getId() + "' ><img align='bottom' src='images/dislike.png' width=50></a></td><td></td>");
            if(profile2 != null)
            {
                pw.println("<td align='center' ><a href='Like?id=" + profile2.getId() + "' ><img align='bottom' src='images/like.png' width=50></a>");
                pw.println("<a href='Dislike?id=" + profile2.getId() + "' ><img align='bottom' src='images/dislike.png' width=50></a></td><td></td>");;
            }
            if(profile3 != null)
            {
                pw.println("<td align='center' ><a href='Like?id=" + profile3.getId() + "' ><img align='bottom' src='images/like.png' width=50></a>");
                pw.println("<a href='Dislike?id=" + profile3.getId() + "' ><img align='bottom' src='images/dislike.png' width=50></a></td><td></td>");
            }
            pw.println("</tr>");
            
            
            
        }
        pw.println("</table>");
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
