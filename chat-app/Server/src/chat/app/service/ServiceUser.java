/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;

import chat.app.connection.DatabaseConnection;
import chat.app.model.Model_Message;
import chat.app.model.Model_Register;
import chat.app.model.Model_User_Account;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MIR HAMZA
 */
public class ServiceUser {

    public ServiceUser() throws SQLException, ClassNotFoundException  {
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
            con.setAutoCommit(false);
            p=con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, data.getUsername());
            p.setString(2, data.getPassword());
            System.out.println("Data inserted");
            p.execute();
            r = p.getGeneratedKeys();
            r.first();
            int userid = r.getInt(1);
            r.close();
            p.close();
            //creating user account
            
            p=con.prepareStatement(INSERT_USER_ACCOUNT);
            p.setInt(1,userid);
            p.setString(2, data.getUsername());
            p.execute();
            p.close();
            con.commit();
            con.setAutoCommit(true);
            message.setAction(true);
            message.setMessage("Registration Completed");
            message.setData(new Model_User_Account(userid, data.getUsername(), "", "", true));
        }
        }
        catch(Exception e){
            message.setAction(false);
            message.setMessage("Service Error");
            System.out.println(e.getMessage());
            try{
                if(con.getAutoCommit() == false){
                    con.rollback();
                    con.setAutoCommit(true);
                }
            }
            catch(Exception el){
                
            }
            
        }
        return message;
    }
    
    public List<Model_User_Account> getUser(int exituser) throws SQLException{
        List<Model_User_Account> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, exituser);
        ResultSet  r = p.executeQuery();
        while(r.next()){
            int userid = r.getInt(1);
            String username = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            list.add(new Model_User_Account(exituser, username, gender , image, true));
        }
        return list;
        
    }
    
   private final String SELECT_USER_ACCOUNT = "select userid,username,gender,imagestring from user_account where status = 1 and userid<>?";  
   private final String INSERT_USER = "insert into user (username,userpass) values(?,?)";
   private final String INSERT_USER_ACCOUNT ="insert into user_account (userid,username) values(?,?)";
   private final String CHECK_USER = "select iduser from user where username=? limit 1";
    
    private final Connection con;
}
