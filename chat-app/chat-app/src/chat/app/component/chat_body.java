/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package chat.app.component;

import chat.app.swing.Scrollbar;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author MIR HAMZA
 */
public class chat_body extends javax.swing.JPanel {

    /**
     * Creates new form chat_body
     */
    public chat_body() {
        initComponents();
        init();
        addItemRight("hello, hi how are you?,Oracle Corporation is an American multinational computer technology company headquartered in Austin, Texas, United States. In 2020, Oracle was the third-largest software company in the world by revenue and market capitalization. In 2023, the company’s seat in Forbes Global 2000 was 80. ");
        addItemRight("hello, hi how are you?,Oracle Corporation is an American multinational computer technology company headquartered in Austin, Texas, United States. In 2020, Oracle was the third-largest software company in the world by revenue and market capitalization. In 2023, the company’s seat in Forbes Global 2000 was 80. ",new ImageIcon(getClass().getResource("/chat/app/icon/testing/pic.jpg")));
        addItemLeft("How are you juicy pussy \n It is so wettttt \n slipperry","Hamza");
        addItemRight("OHHH \n YEAHHHHH");
        addItemLeft("How are you juicy pussy \n It is so wettttt \n slipperry","Hamza");
        addItemRight("OHHH \n YEAHHHHH");
        addItemLeft("How are you juicy pussy \n It is so wettttt \n slipperry","Hamza");
        addDate("20/4/2024");
        String img[] = {"LRMj,K-:?G9G_JIon}WqD~ITRPs,"};
        addItemRight("OHHH \n YEAHHHHH",new ImageIcon(getClass().getResource("/chat/app/icon/testing/cat.png")),new ImageIcon(getClass().getResource("/chat/app/icon/testing/pic.jpg")));
        addItemLeft("How are you juicy pussy \n It is so wettttt \n slipperry", "Hamza");
        addItemRight("OHHH \n YEAHHHHH");
        addItemLeft("How are you juicy pussy \n It is so wettttt \n slipperry","Hamza",new ImageIcon(getClass().getResource("/chat/app/icon/testing/dog.jpg")),new ImageIcon(getClass().getResource("/chat/app/icon/testing/dog.jpg")));
        addItemRight("OHHH \n YEAHHHHH");
        addDate("22/4/2024");
        addItemLeft("","Hamza",img);
        addItemRight("OHHH \n YEAHHHHH");
        addItemLeft("","Hamza",img);
        addItemFile("My file", "Hamza", "Porn.mov", "25 MB");
        addItemFileRight("","Lectures.PDF","60 KB");
    }
    
    private void init(){
        body.setLayout(new MigLayout("fillx","","5[]5"));
        sp.setVerticalScrollBar(new Scrollbar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }
    
    public void addItemLeft(String Text , String user,Icon... image){
        chat_Left_With_Profile item = new chat_Left_With_Profile();
        item.setText(Text);
        item.setImage(image);
        item.setTime();
        item.setUserProfile(user);
        body.add(item,"Wrap, W 100::80%");
        body.repaint();
        body.revalidate();
    }
    
    public void addItemLeft(String Text , String user,String[] image){
        chat_Left_With_Profile item = new chat_Left_With_Profile();
        item.setText(Text);
        item.setImage(image);
        item.setTime();
        item.setUserProfile(user);
        body.add(item,"Wrap, W 100::80%");
        body.repaint();
        body.revalidate();
    }
    
    public void addItemFile(String Text , String user,String filename , String filesize){
        chat_Left_With_Profile item = new chat_Left_With_Profile();
        item.setText(Text);
        item.setFile(filename, filesize);
        item.setTime();
        item.setUserProfile(user);
        body.add(item,"Wrap, W 100::80%");
        body.repaint();
        body.revalidate();
    }
        
    public void addItemRight(String Text,Icon... image){
        chat_Right item = new chat_Right();
        item.setText(Text);
        item.setImage(image);
        item.setTime();
        body.add(item,"Wrap, al right, W 100::80%");
        body.repaint();
        body.revalidate();
    }
    
    public void addItemFileRight(String text,String filename , String filesize){
        chat_Right item = new chat_Right();
        item.setFile(filename, filesize);
        item.setText(text);
        item.setTime();
        body.add(item,"Wrap, al right, W 100::80%");
        body.repaint();
        body.revalidate();
    }
    public void addDate(String Date){
        Chat_Date dateitem = new Chat_Date();
        dateitem.setDate(Date);
        body.add(dateitem, "wrap, al center");
        body.repaint();
        body.revalidate();
    
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
