/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shannon
 */
public class Messages extends HttpServlet {

    @EJB
    private NewSessionBeanRemote newSessionBean;

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
        if(!logged.equals("true"))
        {
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "login.jsp?"); 
        } else {
            user1 = (Integer)session.getAttribute("id");
            
        
        // obtain the values of the form data automatically URL decoded
        
        //int user2 = Integer.parseInt(request.getParameter("id"));
         
        ArrayList<String> contacts = newSessionBean.getContactList(user1);
        //ArrayList<Message> messages = new ArrayList<>();
        ArrayList<Message> messages = newSessionBean.getAllMessages(user1);
        //newSessionBean.getAllMessages(user1);
        //newSessionBean.sendMessage(user1, user1, logged);
        //newSessionBean.getContactList(0);
        PrintWriter pw = response.getWriter();
        
      // set response headers before returning any message content
      response.setContentType("text/html");
      // prepare the content of the response
      pw.println("<!DOCTYPE HTML PUBLIC " + QUOTE +
        "-//W3C//DTD HTML 4.0 Transitional//EN" + QUOTE + ">\n" +
        "<HTML>\n" + "<HEAD>\n" +
        "<TITLE>Tinder 2.0 - Register</TITLE>\n" + "</HEAD>\n" + "<BODY>\n" +
        "<link rel='stylesheet' type='text/css' href='style.css'>" +
        "<div id='container'>" +
        "<div id='header'> <img src='images/logo.png' width=1024></div>");
        request.getRequestDispatcher("/navigation.html").include(request, response);
        request.getRequestDispatcher("/status.jsp").include(request, response);
        pw.println("<div id='main'>" );
        pw.print("<div style='margin:25px'>Send a new message: <form style='margin: 15px; display:inline' action='Message' method='get'><select name='id'>");
        for(int i = 0; i < contacts.size(); i+= 2)
            pw.print("<option value=" + contacts.get(i+1) + ">" + contacts.get(i) + "</option>");
        pw.print("</select><input type='submit' value='Message' /></form> <br><br>");
        if(messages.size() == 0)
            pw.print("No messages :( <br>");
        else {
            pw.print("Last recieved messages: <br> <br>");
        }
        
        for(Message message : messages)
        {
            Profile sender = message.getSender();
            pw.print("<a href='Message?id=" + sender.getId() + "' ><div class='messageItem'><img style='margin-right:25px;' align='left' width='50' src='images/" + sender.getSex() +
                    (sender.getAge() % 10) +  ".jpg'/>" + sender.getName() + ": " + message.getMessage() + "</div></a><br><br>");
        }
        pw.println(
            "</div></div>" +
            "</BODY>\n</HTML>\n");
      pw.close();
        }
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
