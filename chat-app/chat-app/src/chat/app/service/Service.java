/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;


import chat.app.event.PublicEvent;
import chat.app.model.Model_FIle_Sender;
import chat.app.model.Model_Receive_Message;
import chat.app.model.Model_Send_Message;
import chat.app.model.Model_User_Account;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author MIR HAMZA
 */
public class Service {
    private static Service instance;
    private Socket client;
    private final int PORT_NUMBER =9999;
    private final String IP = "localhost";
    private List<Model_FIle_Sender> fileSender;
    
    private Model_User_Account user;

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    public Socket getClient() {
        return client;
    }
    
    public Model_FIle_Sender addFile(File file, Model_Send_Message message) throws IOException{
        Model_FIle_Sender data = new Model_FIle_Sender(file, client, message);
        message.setFile(data);
        fileSender.add(data);
        if(fileSender.size() == 1){
            data.initSend();
        }
        return data;

    }
    
    public void fileSendFinish(Model_FIle_Sender data) throws IOException{
        fileSender.remove(data);
        if(!fileSender.isEmpty()){
            fileSender.get(0).initSend();
        }
    }
    
    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }
    
    public Service(){
        fileSender = new ArrayList<>();
        
    }
    
    public void startServer(){
        try{
        client = IO.socket("http://"+IP+":"+PORT_NUMBER);
        client.on("list_user", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
            
                //list users
                List<Model_User_Account> users = new ArrayList<>();
                for(Object o : os){
                    Model_User_Account u = new Model_User_Account(o);
                    if(u.getUserId() != user.getUserId() ){
                        System.out.println("USer added");
                        users.add(u);
                    }
                    else{
                        System.out.println(o.toString());
                        System.out.println(user.getUserId());
                        System.out.println("user not added");
                    }
                }
                PublicEvent.getInstance().getEventMenuLeft().newUser(users);
            }
        });
        
        client.on("user_status", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
            
                int userId = (Integer) os[0];
                boolean status = (Boolean) os[1];
                if(status){
                    PublicEvent.getInstance().getEventMenuLeft().userConnect(userId);
                }
                else{
                    PublicEvent.getInstance().getEventMenuLeft().userDisconnect(userId);
                }
            }
        });
        
        client.on("receive_ms", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
            
                Model_Receive_Message message = new Model_Receive_Message(os[0]);
                PublicEvent.getInstance().getEventChat().receiveMessage(message);
            }
        });
        
        client.open();
        }
        catch(Exception e){
            error(e);
        }
    }
    
    public void error(Exception e){
        System.err.println(e);
    }

    
}
