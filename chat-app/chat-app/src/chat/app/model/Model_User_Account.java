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
public class Model_User_Account {
    int userId;
    private String userName;
    private String gender;

    public Model_User_Account(int userId, String userName, String gender, String image, boolean status) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.image = image;
        this.status = status;
    }

    public Model_User_Account(Object json) {
        JSONObject obj = (JSONObject) json;
        System.out.println(obj.toString());
        try{
            userId = obj.getInt("userId");
            userName = obj.getString("userName");
            gender = obj.getString("gender");
            image = obj.getString("image");
            status = obj.getBoolean("status");
        }
        catch(JSONException e){
        System.err.println(e);
    }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    private String image;
    private boolean status;
}
