/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package chat.app.form;

import chat.app.component.Item_people;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author MIR HAMZA
 */
public class Menu_Left extends javax.swing.JPanel {

    /**
     * Creates new form Menu_Left
     */
    public Menu_Left() {
        initComponents();
        init();
    }
    public void init(){
        MenuList.setLayout(new MigLayout("fillx","0[]0","1[]1"));
        showpeople();
    }
    private void showpeople(){
        for (int i = 0; i < 8; i++) {
            MenuList.add(new Item_people("People "+i),"Wrap");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuButton2 = new chat.app.component.MenuButton();
        menuButton3 = new chat.app.component.MenuButton();
        menuButton4 = new chat.app.component.MenuButton();
        MenuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setLayout(new javax.swing.BoxLayout(menu, javax.swing.BoxLayout.LINE_AXIS));

        menuButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/app/icon/message_selected.png"))); // NOI18N
        menuButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton2ActionPerformed(evt);
            }
        });
        menu.add(menuButton2);

        menuButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/app/icon/group.png"))); // NOI18N
        menuButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton3ActionPerformed(evt);
            }
        });
        menu.add(menuButton3);

        menuButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/app/icon/box.png"))); // NOI18N
        menuButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton4ActionPerformed(evt);
            }
        });
        menu.add(menuButton4);

        javax.swing.GroupLayout MenuListLayout = new javax.swing.GroupLayout(MenuList);
        MenuList.setLayout(MenuListLayout);
        MenuListLayout.setHorizontalGroup(
            MenuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MenuListLayout.setVerticalGroup(
            MenuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(MenuList, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuList)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton2ActionPerformed

    private void menuButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton3ActionPerformed

    private void menuButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane MenuList;
    private javax.swing.JLayeredPane menu;
    private chat.app.component.MenuButton menuButton2;
    private chat.app.component.MenuButton menuButton3;
    private chat.app.component.MenuButton menuButton4;
    // End of variables declaration//GEN-END:variables
}