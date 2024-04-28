/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.app.form;

import chat.app.component.Chat_Body;
import chat.app.component.Chat_Bottom;
import chat.app.component.Chat_Title;
import chat.app.event.EventChat;
import chat.app.event.PublicEvent;
import chat.app.model.Model_Receive_Message;
import chat.app.model.Model_Send_Message;
import chat.app.model.Model_User_Account;
import net.miginfocom.swing.MigLayout;

public class Chat extends javax.swing.JPanel {

    private Chat_Title chatTitle;
    private Chat_Body chatBody;
    private Chat_Bottom chatBottom;
    
    public Chat() {
        initComponents();
        init();
    }
    
    public void init(){
        setLayout(new MigLayout("fillx","0[fill]0","[]0[100%,fill]0[shrink 0]0"));
      chatTitle = new Chat_Title();
      chatBody = new Chat_Body();
      chatBottom = new Chat_Bottom();
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(Model_Send_Message data) {
                chatBody.addItemRight(data);
            }

            @Override
            public void receiveMessage(Model_Receive_Message data) {
                if(chatTitle.getUser().getUserId() == data.getFromUserID()){
                    chatBody.addItemLeft(data);
                }
            }
            
        });
        add(chatTitle,"wrap");
        add(chatBody,"wrap");
        add(chatBottom,"h ::50%");
      
    }

    public void setUser(Model_User_Account user){
        chatTitle.setUserName(user);
        chatBottom.setUser(user);
        chatBody.clearchat();
        
    }
    
    public void updateUser(Model_User_Account user){
        chatTitle.updateUser(user);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
