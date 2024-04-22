/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import chat.app.swing.blurHash.BlurHash;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author MIR HAMZA
 */
public class Test {
    public static void main(String[] args) {
        try{
            BufferedImage image = ImageIO.read(new File("E:\\FAST UNIVERSITY\\SEMESTER 6\\CN_PROJECT_HELP\\java-swing-chat-application-main\\part 8\\chart-application\\src\\com\\raven\\icon\\testing\\dog.jpg"));
            String blurHash = BlurHash.encode(image); 
            System.out.println(blurHash);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
