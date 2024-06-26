/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.service;

import chat.app.app.MessageType;
import chat.app.connection.DatabaseConnection;
import chat.app.model.Model_File;
import chat.app.model.Model_File_Receiver;
import chat.app.model.Model_File_Sender;
import chat.app.model.Model_Package_Sender;
import chat.app.model.Model_Receive_Image;
import chat.app.model.Model_Send_Message;
import chat.app.swing.blurHash.BlurHash;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author MIR HAMZA
 */
public class ServiceFile {

    public ServiceFile() throws SQLException, ClassNotFoundException {
        this.con = DatabaseConnection.getInstance().getConnection();
        this.fileReceivers = new HashMap<>();
        this.fileSenders = new HashMap<>();
    }
    
    public Model_File addFileReceiver(String fileExtension) throws SQLException{
        Model_File data;
        PreparedStatement p = con.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, fileExtension);
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.first();
        int fileID = r.getInt(1);
        data = new Model_File(fileID, fileExtension);
        r.close();
        p.close();
        return data;
        
    } 
    public void updateBlurHashDone(int fileID, String blurhash) throws SQLException{
        PreparedStatement p = con.prepareStatement(UPDATE_BLUR_HASH_DONE);
        p.setString(1, blurhash);
        p.setInt(2,fileID);
        p.execute();
        p.close();
        
    }
    public void updateDone(int fileID)throws SQLException{
        PreparedStatement p = con.prepareStatement(UPDATE_DONE);
        p.setInt(1,fileID);
        p.execute();
        p.close();
    }
    public void initFile(Model_File file, Model_Send_Message message) throws SQLException, IOException{
     
        fileReceivers.put(file.getFileID(), new Model_File_Receiver(message,toFileObject(file)));
        
    }
    
    public Model_File getFile (int fileID) throws SQLException{
        PreparedStatement p = con.prepareStatement(GET_FILE_EXTENSION);
        p.setInt(1, fileID);
        ResultSet r = p.executeQuery();
        if(r.next()){
            System.out.println("file found");
        String fileExtension = r.getString(1);
        Model_File data = new Model_File(fileID, fileExtension);
            return data;
        }
        else{
            System.out.println("file not found");
        }
        r.close();
        p.close();
        return null;

    }
    
    public synchronized Model_File initFIle(int fileID) throws IOException,SQLException{
        Model_File file;
        if(!fileSenders.containsKey(fileID)){
            file = getFile(fileID);
            fileSenders.put(fileID, new Model_File_Sender(file, new File(PATH_FILE + fileID + file.getFileExtension() )));
        }
        else{
            file = fileSenders.get(fileID).getData();
        }
        return file;
    }
    
    public byte[] getFileData(long currentLength , int fileID) throws IOException,SQLException{
        initFIle(fileID);
        return fileSenders.get(fileID).read(currentLength);
    }
    
    public long getFileSize (int fileID){
        return fileSenders.get(fileID).getFileSize();
    }
    
    public void receiveFile(Model_Package_Sender dataPackage) throws IOException{
        if(!dataPackage.isFinish()){
            fileReceivers.get(dataPackage.getFileID()).writeFile(dataPackage.getData());
        }
        else{
            fileReceivers.get(dataPackage.getFileID()).close();
        }
    }
    
    public Model_Send_Message closeFile(Model_Receive_Image dataImage)throws IOException,SQLException{
        
        Model_File_Receiver file = fileReceivers.get(dataImage.getFileID());
        if(file.getMessage().getMessageType() == MessageType.IMAGE.getValue()){
            
            file.getMessage().setText("");
            String blurhash = convertFileToBlurHash(file.getFile(), dataImage);
            updateBlurHashDone(dataImage.getFileID(), blurhash);
        }
        else{
            updateDone(dataImage.getFileID());
        }
        fileReceivers.remove(dataImage.getFileID());
        return file.getMessage();
    }
    
    private String convertFileToBlurHash(File file, Model_Receive_Image dataImage) throws IOException{
        BufferedImage img = ImageIO.read(file);
        Dimension size = getAutoSize(new Dimension(img.getWidth(),img.getHeight()), new Dimension(200,200));
        BufferedImage newImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(img, 0, 0,size.width,size.height,null);        
        String blurHash = BlurHash.encode(newImage);
        dataImage.setWidth(size.width);
        dataImage.setHeight(size.height);
        dataImage.setImage(blurHash);
        return blurHash;
    }
    
    private Dimension getAutoSize (Dimension fromSize, Dimension toSize){
         int w = toSize.width;
        int h = toSize.height;
        int iw = fromSize.width;
        int ih = fromSize.height;
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        return new Dimension(width,height);
    }
    
    private File toFileObject(Model_File file){
        System.out.println("file saving");
        return new File(PATH_FILE + file.getFileID() + file.getFileExtension()); 
    }
    
    private final String PATH_FILE = "Server_data/";
    private final String INSERT = "insert into files(FileExtension) values (?)";
    private final String UPDATE_BLUR_HASH_DONE = "update files set BlurHash=?, Status='1' where Fileid=? limit 1";
    private final String UPDATE_DONE = "update files set 'Status='1' where Fileid=? limit 1'";
    private final String GET_FILE_EXTENSION = "Select FileExtension from files where Fileid =? limit 1";
    
    private final Connection con;
    private final Map<Integer,Model_File_Receiver> fileReceivers;
    private final Map<Integer,Model_File_Sender> fileSenders;
    
}
