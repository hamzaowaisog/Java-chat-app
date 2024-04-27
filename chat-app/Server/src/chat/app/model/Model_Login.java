/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.model;

/**
 *
 * @author MIR HAMZA
 */
public class Model_Login {
    
   private String username;
   private String Password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Model_Login() {
    }

    public Model_Login(String username, String Password) {
        this.username = username;
        this.Password = Password;
    }
   
   
}
