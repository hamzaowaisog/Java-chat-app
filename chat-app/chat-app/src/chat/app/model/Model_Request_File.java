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
public class Model_Request_File {
    private int fileID;
    private long currentLength;

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public long getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(long currentLength) {
        this.currentLength = currentLength;
    }

    public Model_Request_File() {
    }

    public Model_Request_File(int fileID, long currentLength) {
        this.fileID = fileID;
        this.currentLength = currentLength;
    }
    
    public JSONObject toJsonObject(){
        try {
            JSONObject json = new JSONObject();
            json.put("fileID", fileID);
            json.put("currentLength", currentLength);
            return json;
            
        } catch (JSONException e) {
            return null;
        }
    }
    
    
}
