/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;

/**
 *
 * @author Shannon
 */
public class Message implements Serializable{
    
    private Profile sender;
    private Profile receiver;
    private String message;

    public Message(Profile sender, Profile receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Profile getSender() {
        return sender;
    }

    public Profile getReceiver() {
        return receiver;
    }


    public String getMessage() {
        return message;
    }
    
}
