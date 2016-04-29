/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Shannon
 */
@Stateless
public class NewSessionBean implements NewSessionBeanRemote {

    Connection conn;
    
    @Override
    public String businessMethod(String name) {
        return "hello" + name;
    }
    

    @Override
    public String sendMessage(int recipient, int sender, String message) 
    {
        dbConnect();
        String sql = "";
        ResultSet rs;
        String report = "Message failed";
        try {
            Statement stmt = conn.createStatement();
            sql = "INSERT INTO message (sender, receiver, message)"
                    + " VALUES(" + sender + ", " + recipient + ", '" + message + "')";
            stmt.executeUpdate(sql);
            report = "Message sent!";
        } catch (Exception e) {
            //message = e.getMessage();
            //e.printStackTrace(pw);
        }
        return report;
    }
    
    

    @Override
    public ArrayList<String> getContactList(int id) {
        ArrayList<String> list = new ArrayList<>();
        dbConnect();
        
        String sql = "";
        ResultSet rs;
        try {
            Statement stmt = conn.createStatement();
            //sql = "SELECT * FROM swipe WHERE user1_id = " + id;
            sql = "SELECT swipe.user2_id, account.username, account.account_id FROM swipe "
                    + "INNER JOIN account ON swipe.user2_id = account.account_id AND swipe.user1_id = " + id + " ORDER BY swipe_id";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                list.add(rs.getString("username"));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public ArrayList<Message> getAllMessages(int id) 
    {
        ArrayList<Message> list = new ArrayList<>();
        dbConnect();
        
        String sql = "";
        ResultSet rs;
        try {
            Statement stmt = conn.createStatement();
            //sql = "SELECT * FROM swipe WHERE user1_id = " + id;
            sql = "SELECT max(message.message), account_sender.full_name AS sender, account_sender.account_id AS sender_id, account_sender.age AS sender_age, account_sender.sex AS sender_sex," +
                    " account_receiver.full_name AS receiver, account_receiver.account_id AS receiver_id, account_receiver.age AS receiver_age, account_receiver.sex AS receiver_sex FROM message " +
                    " INNER JOIN account AS account_sender ON message.sender_id = account_sender.account_id AND message.receiver_id = " + id +
                    " INNER JOIN account AS account_receiver ON message.receiver_id = account_receiver.account_id AND message.receiver_id = " + id +
                    " GROUP BY message.sender_id " +
                    " ORDER BY message_id";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                //System.out.println(rs.getString("sender") + rs.getString("receiver") + rs.getInt("sender_id") + rs.getInt("receiver_id")+ rs.getString("max(message.message)"));
                Profile sender = new Profile(rs.getString("sender"), rs.getInt("sender_age"), rs.getString("sender_sex"), rs.getInt("sender_id"));
                Profile receiver = new Profile(rs.getString("receiver"), rs.getInt("receiver_age"), rs.getString("receiver_sex"), rs.getInt("receiver_id"));
                list.add(new Message(sender, receiver, rs.getString("max(message.message)")));
                //System.out.println(list.get(list.size() - 1));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
            System.err.println("size is :" +list.size());
        return list;
    }
    
    
    private void dbConnect()
    {
        String url= "jdbc:mysql://localhost:3306/tinder";
        String usernameDB = "root";
        String passwordDB = "";
        
        try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(url, usernameDB, passwordDB);
        } catch (Exception e) {
              //e.printStackTrace(pw);
              
        }
    }
    
    
}
