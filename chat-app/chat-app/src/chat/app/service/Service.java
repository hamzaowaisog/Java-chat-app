/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;


import io.socket.client.IO;
import io.socket.client.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author MIR HAMZA
 */
public class Service {
    private static Service instance;
    private Socket client;
    private final int PORT_NUMBER =9999;

    public Socket getClient() {
        return client;
    }
    
    private final String IP = "localhost";
    
    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }
    
    public Service(){
        
    }
    
    public void startServer(){
        try{
        client = IO.socket("http://"+IP+":"+PORT_NUMBER);
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
