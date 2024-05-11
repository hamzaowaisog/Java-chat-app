/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author MIR HAMZA
 */
public class Model_Message_Fetch {
        private int user_id;
    private String message;
    private boolean isright;
    private int receiver_id;
    private LocalTime time;

    public Model_Message_Fetch(int user_id, String message, boolean isright, int receiver_id, LocalTime time) {
        this.user_id = user_id;
        this.message = message;
        this.isright = isright;
        this.receiver_id = receiver_id;
        this.time = time;
    }

    public Model_Message_Fetch() {
    }
    public Model_Message_Fetch(Object json) {
    JSONObject obj = (JSONObject) json;
    System.out.println("Received JSON: " + obj.toString());
    try {
        user_id = obj.getInt("user_id");
        message = obj.getString("message");
        isright = obj.getBoolean("isright");
        receiver_id = obj.getInt("receiver_id");
        
        JSONObject timeObj = obj.getJSONObject("time");
        int hour = timeObj.getInt("hour");
        int minute = timeObj.getInt("minute");
        int second = timeObj.getInt("second");

        time = LocalTime.of(hour, minute, second);
     
        Iterator<String> keys = obj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            keys.remove();
        }
       timeObj.remove("hour");
       timeObj.remove("minute"); 
       timeObj.remove("second");
       
    } catch (Exception e) {
        System.err.println("Error parsing JSON: " + e.getMessage());
    }
        
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

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    
}
