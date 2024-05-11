/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.model;

import org.json.JSONObject;

/**
 *
 * @author MIR HAMZA
 */
public class Model_Message_Insert {
    private int user_id;
    private String message;
    private boolean isright;
    private int receiver_id;

    public Model_Message_Insert() {
    }

    public Model_Message_Insert(int user_id, String message, boolean isright,int receiver_id) {
        this.user_id = user_id;
        this.message = message;
        this.isright = isright;
        this.receiver_id = receiver_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }
    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsright() {
        return isright;
    }

    public void setIsright(boolean isright) {
        this.isright = isright;
    }
    
    public JSONObject toJSONObject(){
        try {
            JSONObject json = new JSONObject();
            json.put("user_id",user_id);
            json.put("receiver_id",receiver_id);
            json.put("isright",isright);
            json.put("message", message);
            
            return json;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    
    
}
