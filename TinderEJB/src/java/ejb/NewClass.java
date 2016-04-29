/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;

/**
 *
 * @author Shannon
 */
public class NewClass {
    public static void main(String[] args)
    {
        
         ArrayList<Message> messages = new NewSessionBean().getAllMessages(5);
         for(Message message : messages)
             System.err.println(message.getMessage());
    }
}
