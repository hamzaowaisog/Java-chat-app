/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.swing;

import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author MIR HAMZA
 */
public class Line extends JLabel{
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(getForeground());
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    }
    
}
