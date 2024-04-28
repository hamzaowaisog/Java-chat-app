/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;

import chat.app.connection.DatabaseConnection;
import chat.app.model.Model_Client;
import chat.app.model.Model_Login;
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
            list.add(new Model_User_Account(userid, username, gender , image, checkUserStatus(userid)));
        }
        return list;
        
    }
    
    public Model_User_Account login(Model_Login login) throws SQLException{
        Model_User_Account data = null;
        PreparedStatement p = con.prepareStatement(LOGIN);
        p.setString(1, login.getUsername());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        
        if(r.next()){
            System.out.println("Data found");
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            System.out.println("data extracted");
            data = new Model_User_Account(userID,userName,gender, image, true);
            
        }
        else{
            System.out.println("Data not found");
        }
        r.close();
        p.close();
        return data;
    }
    
    private boolean checkUserStatus(int userID){
        List<Model_Client> clients = Service.getInstance(null).getListClient();
        for (Model_Client c : clients){
            if(c.getUser().getUserId() == userID){
                System.out.println("active");
                return true;
            }
        }
        System.out.println("not Active");
        return false;
    }
    
   private final String LOGIN = "Select iduser , user_account.username , user_account.gender ,user_account.imagestring from user join user_account on user.iduser = user_account.userid where user.username =BINARY(?) and user.userpass = BINARY(?) and user_account.status='1'"; 
   private final String SELECT_USER_ACCOUNT = "select userid,username,gender,imagestring from user_account where status = 1 and userid <> ?";  
   private final String INSERT_USER = "insert into user (username,userpass) values(?,?)";
   private final String INSERT_USER_ACCOUNT ="insert into user_account (userid,username) values(?,?)";
   private final String CHECK_USER = "select iduser from user where username=? limit 1";
    
    private final Connection con;
}
