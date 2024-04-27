/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package chat.app.event;

import chat.app.model.Model_Register;

/**
 *
 * @author MIR HAMZA
 */
public interface EventLogin {
    public void login();
    public void register(Model_Register data, EventMessage message);
    public void goRegister();
    public void goLogin();
    
}
