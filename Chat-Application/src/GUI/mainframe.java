package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class mainframe extends JFrame {
    JFrame jf;
    ComponentResizer cr;
    JPanel background,uppersec;
    int pX,pY;
    JButton minimise,close;
    JLayeredPane body;

    public mainframe(){
        jf = new JFrame();
        jf.setSize(1053, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setUndecorated(true);
        jf.setVisible(true);
        cr = new ComponentResizer();
        cr.registerComponent(jf);
        cr.setMinimumSize(new Dimension(800,500));
        cr.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        cr.setSnapSize(new Dimension(10,10));

        background = new JPanel();
        background.setBounds(0,0,1053,600);
        background.setBackground(new Color(0,0,0,0));
        jf.add(background);

        uppersec = new JPanel();
        uppersec.setBounds(0,0,1053,25);
        uppersec.setBackground(new Color(200, 200, 200));
        uppersec.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 3));
        jf.add(uppersec);

        close = new JButton(new ImageIcon("src/icon/close.png"));
        close.setBorder(null);
        close.setBackground(null);
        close.setSize(25, 25);
        close.setLocation(uppersec.getWidth() - close.getWidth() - 5, 0);
        close.setVisible(true);
        close.addActionListener(e -> System.exit(0));

        minimise = new JButton(new ImageIcon("src/icon/minimize.png"));
        minimise.setBorder(null);
        minimise.setBackground(null);
        minimise.setSize(25, 25);
        minimise.setLocation(uppersec.getWidth() - minimise.getWidth() - 35, 0);
        minimise.addActionListener(e -> jf.setState(JFrame.ICONIFIED));
        minimise.setVisible(true);
        uppersec.add(minimise);
        uppersec.add(close);
        uppersec.revalidate();

        body = new JLayeredPane();
        body.setBounds(0,25,1053,575);
        body.setLayout(new BorderLayout());
        body.setVisible(true);
        jf.add(body);

        background.revalidate();
        jf.repaint();


        jf.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(jf.getSize());
                uppersec.setSize(jf.getWidth(), 25);
                body.setSize(jf.getWidth(), jf.getHeight() - 25);
                body.revalidate();
                jf.revalidate();
                minimise.setLocation(uppersec.getWidth() - minimise.getWidth() - 35, 0);
                close.setLocation(uppersec.getWidth() - close.getWidth() - 5, 0);

            }
        });

        uppersec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pX = e.getX();
                pY = e.getY();
            }
        });
        uppersec.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                jf.setLocation(jf.getLocation().x + e.getX() - pX,
                        jf.getLocation().y + e.getY() - pY);
            }
        });

        jf.setIconImage(new ImageIcon("src/icon/icon.png").getImage());
        System.out.println("Mainframe created"+body.isVisible());



    }

}
