/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.model;

/**
 *
 * @author MIR HAMZA
 */
public class Model_Receive_Message {
    
    private int fromUserID;
    private String text;

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Receive_Message() {
        
    }

    public Model_Receive_Message(int fromUserID, String text) {
        this.fromUserID = fromUserID;
        this.text = text;
    }
    
}
