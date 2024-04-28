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

    public Model_Receive_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try{
            fromUserID = obj.getInt("fromUserID");
            text = obj.getString("text");
            
        }
        catch(JSONException e){
        
            System.err.println(e);
        
        }
    }

    public Model_Receive_Message(int fromUserID, String text) {
        this.fromUserID = fromUserID;
        this.text = text;
    }
    
    
    
    public JSONObject toJsonObject(){
        try{
            JSONObject json = new JSONObject();
            json.put("fromUserID", fromUserID);
            json.put("text", text);
            return json;
        }
        catch(JSONException e){
            return null;
        }
    }
    
    
    
}
