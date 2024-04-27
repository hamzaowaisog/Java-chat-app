/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author MIR HAMZA
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() throws SQLException, ClassNotFoundException{
        if(instance == null){
            instance = new DatabaseConnection();
            instance.connectToDatabase();
        }
        return instance;
    }
    
    private DatabaseConnection(){
        
    }
    public void connectToDatabase() throws SQLException, ClassNotFoundException{
        String server = "localhost";
        String port = "3306";
        String database = "chat_application";
        String username = "root";
        String password = "root";
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application?useSSL=false", "root", "root");
    }
        public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    
}
