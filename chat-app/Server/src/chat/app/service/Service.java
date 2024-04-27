/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;

import chat.app.model.Model_Message;
import chat.app.model.Model_Register;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
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
    
    public static Service getInstance(JTextArea textArea){
        if(instance == null){
            instance = new Service(textArea);
        }
        return instance;
    }
    
    public Service(JTextArea textArea){
        this.textArea = textArea;
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
                Model_Message message = new ServiceUser().register(t);
                ar.sendAckData(message.isAction(),message.getMessage());
                textArea.append("User Registered :"+t.getUsername()+" Password "+t.getPassword()+"\n");
            }
        });
        server.start();
        textArea.append("Server has started on Port :"+PORT_NUMBER+"\n");
    }

    
}
