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
public class Model_Data_message {
    private int sender_id;
    private int receiver_id;

    public Model_Data_message() {
    }

    public Model_Data_message(int sender_id, int receiver_id) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }
   
    public JSONObject toJSONObject(){
        try {
            JSONObject json = new JSONObject();
            json.put("sender_id",sender_id);
            json.put("receiver_id", receiver_id);
            return json;
            
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
