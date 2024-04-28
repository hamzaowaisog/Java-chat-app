/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author MIR HAMZA
 */
public class Model_Send_Message {
    
    private int fromUserID;
    private int toUserID;
    private String text;

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Send_Message() {
    }

    public Model_Send_Message(int fromUserID, int toUserID, String text) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.text = text;
    }
    
    public JSONObject toJsonObject(){
        try{
            JSONObject json = new JSONObject();
            json.put("fromUserID", fromUserID);
            json.put("toUserID",toUserID);
            json.put("text", text);
            return json;
        }
        catch(JSONException e){
            return null;
        }
    }
    
    
    
}