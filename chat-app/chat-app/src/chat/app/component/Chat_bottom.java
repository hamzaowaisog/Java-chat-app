/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.app.component;

import chat.app.app.MessageType;
import chat.app.event.PublicEvent;
import chat.app.model.Model_Message_Insert;
import chat.app.model.Model_Send_Message;
import chat.app.model.Model_User_Account;
import chat.app.service.Service;
import chat.app.swing.JIMSendTextPane;
import chat.app.swing.ScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Chat_Bottom extends javax.swing.JPanel {

    /**
     * Creates new form Chat_Title
     */
    
   private Model_User_Account user;

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
        panelMore.setUser(user);
    }
   
   
    
    public Chat_Bottom() {
        initComponents();
        init();
    }
    
    public void init(){
        mig = new MigLayout("fillx, filly","0[fill]0[]0[]2","2[fill]2[]0");
        setLayout(mig);
        JScrollPane scroll = new JScrollPane();
        JIMSendTextPane txt = new JIMSendTextPane();
        scroll.setBorder(null);
        txt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
            
                refresh();
                if(e.getKeyChar() == 10 && e.isControlDown()){
                    eventSend(txt);
                    
                }
            }
            
        });
        txt.setBorder(new EmptyBorder(5,5,5,5));
        txt.setHintText("Write Message Here......");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(242,242,242));
        sb.setPreferredSize(new Dimension(2,10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly","0[]3[]0","0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.white);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/chat/app/icon/send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                eventSend(txt);
                
            }
        });
        JButton cmdMore = new JButton();
        cmdMore.setBorder(null);
        cmdMore.setContentAreaFilled(false);
        cmdMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMore.setIcon(new ImageIcon(getClass().getResource("/chat/app/icon/more_disable.png")));
        cmdMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panelMore.isVisible()){
                   cmdMore.setIcon(new ImageIcon(getClass().getResource("/chat/app/icon/more_disable.png")));
                   panelMore.setVisible(false);
                   mig.setComponentConstraints(panelMore,"dock south,h 0!" );
                   revalidate();
                }
                else{
                   cmdMore.setIcon(new ImageIcon(getClass().getResource("/chat/app/icon/more.png")));
                   panelMore.setVisible(true);
                   mig.setComponentConstraints(panelMore,"dock south,h 170!" );
                   revalidate();
                }
            }
        });
        panel.add(cmdMore);
        panel.add(cmd);
        add(panel,"wrap");
        panelMore = new Panel_More();
        panelMore.setVisible(false);
        add(panelMore,"dock south,h 0!");
         
    }
    
    private void eventSend(JIMSendTextPane txt){
        String text = txt.getText().trim();
                if(!text.equals("")){
                    Model_Send_Message message = new Model_Send_Message(MessageType.TEXT,Service.getInstance().getUser().getUserId(),user.getUserId(),text);
                    send(message);
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                    Model_Message_Insert insert = new Model_Message_Insert(Service.getInstance().getUser().getUserId(), text, true, user.getUserId());
                    insert(insert);
                    Model_Message_Insert insert1 = new Model_Message_Insert(user.getUserId(), text, false, Service.getInstance().getUser().getUserId());
                    insert(insert1);
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                }
                else{
                    txt.grabFocus();
                    
                }
        
    }
    private void insert(Model_Message_Insert insert){
        System.out.println("user_id"+insert.getUser_id());
        System.out.println("Receiver_id"+insert.getReceiver_id());
        Service.getInstance().getClient().emit("sending_message",insert.toJSONObject());
    }
    
    private void send(Model_Send_Message data){
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
        
    }
    
    private void refresh(){
        revalidate();
    }
    
    private MigLayout mig;
    private Panel_More panelMore;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
