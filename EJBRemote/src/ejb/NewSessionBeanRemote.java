/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Shannon
 */
@Remote
public interface NewSessionBeanRemote {

    String businessMethod(String name);

    String sendMessage(int recipient, int sender, String message);

    ArrayList<String> getContactList(int id);

    ArrayList<Message> getAllMessages(int id);

    ArrayList<Message> getMessagesFromUser(int user1_id, int user2_id);
    
}
