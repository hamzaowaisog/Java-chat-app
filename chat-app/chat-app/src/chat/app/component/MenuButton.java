/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.component;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.Icon;
/**
 *
 * @author MIR HAMZA
 */
public class MenuButton extends JButton {
    private Icon iconsimple;
    private Icon iconselected;

    public Icon getIconsimple() {
        return iconsimple;
    }

    public void setIconsimple(Icon iconsimple) {
        this.iconsimple = iconsimple;
    }

    public Icon getIconselected() {
        return iconselected;
    }

    public void setIconselected(Icon iconselected) {
        this.iconselected = iconselected;
    }
    public MenuButton(){
        setContentAreaFilled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR) {
        });
        
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if(b){
            setIcon(iconselected);
        }
        else{
            setIcon(iconsimple);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if(isSelected()){
            g.setColor(new Color(110,213,255));
            g.fillRect(0, getHeight()-3, getWidth(), getHeight());
            
        }
    }
    
    
}
