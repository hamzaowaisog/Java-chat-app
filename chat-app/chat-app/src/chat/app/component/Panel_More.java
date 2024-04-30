/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package chat.app.component;

import chat.app.app.MessageType;
import chat.app.emoji.Emoji;
import chat.app.emoji.Model_Emoji;
import chat.app.event.PublicEvent;
import chat.app.main.Main;
import chat.app.model.Model_Send_Message;
import chat.app.model.Model_User_Account;
import chat.app.service.Service;
import chat.app.swing.ScrollBar;
import chat.app.swing.WrapLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author MIR HAMZA
 */
public class Panel_More extends javax.swing.JPanel {

    /**
     * Creates new form Panel_More
     */
    
    private Model_User_Account user;

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }
   
   
    
    public Panel_More() {
        initComponents();
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fillx"));
        panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.LINE_AXIS));
        panelHeader.add(getbuttonImage());
        panelHeader.add(getButtonFile());
        panelHeader.add(getEmojiStyle1());
        panelHeader.add(getEmojiStyle2());
        add(panelHeader,"w 100% , h 30!, wrap");
        
        panelDetail = new JPanel();
        panelDetail.setLayout(new WrapLayout(WrapLayout.LEFT));
        JScrollPane ch = new JScrollPane(panelDetail);
        ch.setBorder(null);
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ch.setVerticalScrollBar(new ScrollBar());
        
        add(ch,"w 100%,h 100%");
        
    }
    
    private JButton getbuttonImage(){
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(getClass().getResource("/chat/app/icon/image.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(true);
                ch.setFileFilter(new FileFilter(){
                    @Override
                    public boolean accept(File f) {
                    
                        return f.isDirectory() || isImageFile(f);
                        
                    }

                    @Override
                    public String getDescription() {
                        return "Image File";
                    }
                
                });
                int option = ch.showOpenDialog(Main.getFrames()[0]);
                if(option == JFileChooser.APPROVE_OPTION){
                    File files[] = ch.getSelectedFiles();
                    try{
                        for(File file:files){
                            Model_Send_Message message = new Model_Send_Message(MessageType.IMAGE,Service.getInstance().getUser().getUserId(),user.getUserId(),"");
                            Service.getInstance().addFile(file, message);
                            PublicEvent.getInstance().getEventChat().sendMessage(message);
                            
                        }
                    }
                    catch (IOException ex){
                        ex.printStackTrace();
                        
                    }
                      
                    
                }

            }
        });
        
        return cmd;
    }
    
    private JButton getButtonFile(){
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(getClass().getResource("/chat/app/icon/link.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                JFileChooser ch = new JFileChooser();
                ch.showOpenDialog(Main.getFrames()[0]);
            }
        });
        return cmd;
    }
    
    private JButton getEmojiStyle1(){
      OptionButton cmd = new OptionButton();
      cmd.setIcon(Emoji.getInstance().getEmoji(1).toSize(25, 25).getIcon());
      cmd.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
              clearSelected();
              cmd.setSelected(true);
         
              panelDetail.removeAll();
              for(Model_Emoji d : Emoji.getInstance().getStyle1()){
                  panelDetail.add(getButton(d));
              }
              
              
              panelDetail.repaint();
              panelDetail.revalidate();
              
          }
      });
    
      return cmd;
    }
    private JButton getEmojiStyle2(){
      OptionButton cmd = new OptionButton();
      cmd.setIcon(Emoji.getInstance().getEmoji(21).toSize(25, 25).getIcon());
      cmd.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
         
              clearSelected();
              cmd.setSelected(true);
              panelDetail.removeAll();
              for(Model_Emoji d : Emoji.getInstance().getStyle2()){
                  panelDetail.add(getButton(d));
              }
              
              
              panelDetail.repaint();
              panelDetail.revalidate();
              
          }
      });
    
      return cmd;
    }
    
    private JButton getButton(Model_Emoji data){
        JButton cmd = new JButton(data.getIcon());
        cmd.setName(data.getId()+"");
        cmd.setBorder(new EmptyBorder(3,3,3,3));
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setContentAreaFilled(false);
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model_Send_Message message = new Model_Send_Message(MessageType.EMOJI,Service.getInstance().getUser().getUserId(),user.getUserId(),data.getId()+"");
                sendMessage(message);
                PublicEvent.getInstance().getEventChat().sendMessage(message);
            }
        });
        return cmd;                 
        
    }
    
    private void sendMessage(Model_Send_Message data){
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }
    
    private void clearSelected(){
        for(Component c : panelHeader.getComponents()){
            if(c instanceof OptionButton){
                ((OptionButton)c).setSelected(false);
            }
        }
    }
    
    private boolean isImageFile(File file){
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".png")||name.endsWith(".jpeg")||name.endsWith(".gif");
    }
    
    private JPanel panelHeader;
    private JPanel panelDetail;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
