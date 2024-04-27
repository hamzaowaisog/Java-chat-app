/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;

import chat.app.connection.DatabaseConnection;
import chat.app.model.Model_Message;
import chat.app.model.Model_Register;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author MIR HAMZA
 */
public class ServiceUser {

    public ServiceUser() throws SQLException, ClassNotFoundException {
        this.con = DatabaseConnection.getInstance().getConnection();
    }
    
    public Model_Message register(Model_Register data) throws SQLException{
        
        Model_Message message = new Model_Message();
        System.out.println("username from model "+data.getUsername());
        System.out.println("password from model "+data.getPassword());
        System.out.println(con.getMetaData().getURL());
        try{
        PreparedStatement p = con.prepareStatement(CHECK_USER);
        p.setString(1,data.getUsername());
        ResultSet r = p.executeQuery();
        if(r.next()){
            message.setAction(false);
            message.setMessage("User already exist");
            System.out.println("Data exist");
        }
        else{
            message.setAction(true);
        }
        r.close();
        p.close();
        if(message.isAction()){
            
            p=con.prepareStatement(INSERT_USER);
            p.setString(1, data.getUsername());
            p.setString(2, data.getPassword());
            System.out.println("Data inserted");
            p.execute();
            p.close();
            message.setAction(true);
            message.setMessage("Registration Completed");
        }
        }
        catch(Exception e){
            message.setAction(false);
            message.setMessage("Service Error");
            System.out.println(e.getMessage());
            
        }
        return message;
    }
    
   private final String INSERT_USER = "insert into user (username,userpass) values(?,?)";
   private final String CHECK_USER = "select iduser from user where username=? limit 1";
    
    private final Connection con;
}
