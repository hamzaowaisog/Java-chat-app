/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;

import chat.app.model.Model_Message;
import chat.app.model.Model_Register;
import chat.app.model.Model_User_Account;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author MIR HAMZA
 */
public class Service {
    private static Service instance;
    private SocketIOServer server;
    private final int PORT_NUMBER =9999;
    private JTextArea textArea;
    private ServiceUser serviceuser;
    
    public static Service getInstance(JTextArea textArea){
        if(instance == null){
            try {
                instance = new Service(textArea);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    
    public Service(JTextArea textArea) throws SQLException, ClassNotFoundException{
        this.textArea = textArea;
        serviceuser = new ServiceUser();
    }
    
    public void startServer(){
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient sioc) {
                textArea.append("one client connected\n");
            }
        });
        
        server.addEventListener("register", Model_Register.class, new DataListener<Model_Register>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Register t, AckRequest ar) throws Exception {
                System.out.println("username "+t.getUsername());
                System.out.println("password "+t.getPassword());
                Model_Message message = serviceuser.register(t);
                ar.sendAckData(message.isAction(),message.getMessage(),message.getData());
                if(message.isAction()){
                textArea.append("User Registered :"+t.getUsername()+" Password "+t.getPassword()+"\n");
                server.getBroadcastOperations().sendEvent("list_user",(Model_User_Account)message.getData());
                }
            }
        });
        
        server.addEventListener("list_user", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer t, AckRequest ar) throws Exception {
         
            try{
                List<Model_User_Account> list = serviceuser.getUser(t);
                sioc.sendEvent("list_user", list.toArray());
            }
            catch (Exception e){
                
            }
                
                
            }
        });
        
        server.start();
        textArea.append("Server has started on Port :"+PORT_NUMBER+"\n");
    }

    
}
