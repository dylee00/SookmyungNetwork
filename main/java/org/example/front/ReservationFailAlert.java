package org.example.front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationFailAlert extends JFrame implements ActionListener {

    JLabel      message1, message2;
    ImagePanel  image;
    JPanel banner;

    public ReservationFailAlert() {
        setLayout(null);
        setResizable(false);
        // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        banner = new JPanel();
        ImageIcon b = new ImageIcon("bannerConfirm.png");
        JLabel l = new JLabel(b, JLabel.CENTER);
        banner.setBounds(0,0,500,150);
        banner.add(l);
        setBackground(new Color(255, 255, 255));

        image = new ImagePanel(new ImageIcon("failnoonsong.png").getImage());
        image.setBounds(150,230,170,180);
        // image.setBackground(Color.white);

        message1 = new JLabel("예약이 실패되었습니다.");
        message1.setFont(new Font("맑은 고딕", Font.BOLD,17));
        message1.setForeground(Color.black);
        message1.setBackground(Color.white);
        message1.setBounds(155, 400, 300, 20);

        //확인버튼
        JButton confirm_button = new JButton(new ImageIcon("btnConfirm.png"));
        confirm_button.setBounds(115,410,270,180);
        confirm_button.setBorderPainted(false);
        confirm_button.setContentAreaFilled(false);
        confirm_button.addActionListener(this);

        add(banner);
        add(image);
        add(message1);
        add(confirm_button);

    }
    class ImagePanel extends JPanel {
        Image img;

        public ImagePanel(Image img) {
            this.img = img;
            setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose(); // 현재 프레임 닫기
    }

}
