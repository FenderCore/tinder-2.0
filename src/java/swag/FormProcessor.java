package swag;

/**
   A servlet that demonstrates how a response can be prepared for an
   HTTP request that originated from a HTML form with text fields
   named firstname and lastname. Note that the servlet requires some
   Java Enterprise edition API and a compatible web server
   @author Andrew Ensor
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.Statement;

public class FormProcessor extends HttpServlet
{
   private final char QUOTE = '"';
   
    Connection conn;

   public void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException
   {  // obtain the values of the form data automatically URL decoded
      String firstName = request.getParameter("firstname");
      String lastName = request.getParameter("lastname");
      String url= "jdbc:mysql://raptor:3306/universitydb";
      String username = "student";
        String password = "fpn871";
        String message = "User not found";
        String sql = "";
      PrintWriter pw = response.getWriter();
      try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
      } catch (Exception e) {
            e.printStackTrace(pw);
        }
        ResultSet rs;
        
        try {
            Statement stmt = conn.createStatement();
            sql = "SELECT * FROM Students WHERE G_Name = '" + firstName + "' AND S_Name = '" + lastName + "'";
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                message = "User found";
            }
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace(pw);
        }
      // set response headers before returning any message content
      response.setContentType("text/html");
      // prepare the content of the response
      pw.println("<!DOCTYPE HTML PUBLIC " + QUOTE +
         "-//W3C//DTD HTML 4.0 Transitional//EN" + QUOTE + ">\n" +
         "<HTML>\n" + "<HEAD>\n" +
         "<TITLE>FormProcessor</TITLE>\n" + "</HEAD>\n" + "<BODY>\n" +
         "<H1>FormProcessor Response</H1>\n" +
         "<P>Hello "+filter(firstName)+" "+filter(lastName)+"</P>\n"+
              message +
         "</BODY>\n</HTML>\n");
      pw.close();
   }
   
   public void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException
   {  doGet(request, response);
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
}
