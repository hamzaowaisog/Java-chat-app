/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.model;


/**
 *
 * @author MIR HAMZA
 */
public class Model_Register {
   private String username;
   private String password;

    public Model_Register(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Model_Register() {
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
}
