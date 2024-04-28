/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package chat.app.event;

import chat.app.model.Model_User_Account;

/**
 *
 * @author MIR HAMZA
 */
public interface EventMain {
    public void showLoading(boolean show);
    public void initchat();
    public void selectUser (Model_User_Account user);
    public void updateUser(Model_User_Account user);
    
    
}
