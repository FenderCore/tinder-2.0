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
        
         new NewSessionBean().sendMessage(6, 5, "hi there");
         //ArrayList<Message> messages = new NewSessionBean().getMessagesFromUser(5, 6);
         //for(Message message : messages)
             //System.err.println(message.getMessage());
    }
}
