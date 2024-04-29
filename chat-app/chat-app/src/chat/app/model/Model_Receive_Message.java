/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.model;

import chat.app.app.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author MIR HAMZA
 */
public class Model_Receive_Message {
    
    
    private int fromUserID;
    private String text;
    private MessageType messageType;

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

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Model_Receive_Message(MessageType messageType,int fromUserID, String text) {
        this.fromUserID = fromUserID;
        this.text = text;
        this.messageType = messageType;
    }

    public Model_Receive_Message() {
    }
    
    
    
   
    
    
    public Model_Receive_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try{
            messageType = MessageType.toMessageType(obj.getInt("messageType"));
            fromUserID = obj.getInt("fromUserID");
            text = obj.getString("text");
            
        }
        catch(JSONException e){
        
            System.err.println(e);
        
        }
    }
    
    public JSONObject toJsonObject(){
        try{
            JSONObject json = new JSONObject();
            json.put("messageType",messageType.getValue());
            json.put("fromUserID", fromUserID);
            json.put("text", text);
            return json;
        }
        catch(JSONException e){
            return null;
        }
    }
    
    
    
}
