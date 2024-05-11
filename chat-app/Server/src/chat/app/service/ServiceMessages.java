/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;

import chat.app.connection.DatabaseConnection;
import chat.app.model.Model_Data_message;
import chat.app.model.Model_Message_Fetch;
import chat.app.model.Model_Message_Insert;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author MIR HAMZA
 */
public class ServiceMessages {
    public ServiceMessages() throws SQLException, ClassNotFoundException{
        this.con = DatabaseConnection.getInstance().getConnection();
        
    }
    public void update_message (Model_Message_Insert message){
        
        System.out.println("Inserting into message");
        System.out.println("senderid"+message.getUser_id());
        System.out.println("Receiverid"+message.getReceiver_id());
        try {
            PreparedStatement p = con.prepareStatement(Insert_Message);
            p.setInt(1, message.getUser_id());
            p.setInt(2, message.getReceiver_id());
            p.setBoolean(3, message.isIsright());
            p.setString(4, message.getMessage());
            p.execute();
            p.close();
            
        } catch (Exception e) {
        }
        
    }
    public List<Model_Message_Fetch> fetch_message (Model_Data_message data){
        System.out.println("Fetching Message");
        List<Model_Message_Fetch> mess = new ArrayList<>();
        try {
           PreparedStatement p = con.prepareStatement(fetch_message);
            System.out.println("Sender id "+data.getSender_id());
            System.out.println("Receiver id "+ data.getReceiver_id());
            p.setInt(1, data.getSender_id());
            p.setInt(2,data.getReceiver_id());
            ResultSet r = p.executeQuery();
            while(r.next()){
                int user = r.getInt("sender_id");
                int rec = r.getInt("receiver_id");
                boolean right = r.getBoolean("r_message");
                String msg = r.getString("message_content");
                String time = r.getString("timestamp");
                LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalTime localTime = dateTime.toLocalTime();
                mess.add(new Model_Message_Fetch(user, msg, right, rec, localTime));
            }
        } catch (SQLException ex) {
            System.out.println("Error Fetching data");
        }
        
        return mess;
    }
    private final Connection con;
    private final String Insert_Message = "Insert into messages (sender_id,receiver_id,r_message,message_content) values (?,?,?,?)";
    private final String fetch_message = "Select * from messages where sender_id =? and receiver_id=?";
    
    
    
    
}
