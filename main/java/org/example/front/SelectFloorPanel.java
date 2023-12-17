package org.example.front;

import org.example.Main;
//import org.example.reservation.LoginMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFloorPanel extends JPanel implements ActionListener {
//    private LoginMenuController lmc = new LoginMenuController();

    private JButton secondFloor, fifthFloor;
    private Main win;
    private Image image1;
    private JButton btnback; //뒤로가기 버튼

    public SelectFloorPanel(Main win) {
        this.win = win;
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        secondFloor = new JButton(new ImageIcon("btnSecondFloor.png"));
        fifthFloor = new JButton(new ImageIcon("btnFifthFloor.png"));

        secondFloor.setBounds(35,340,200,200);
        fifthFloor.setBounds(255,340,200,200);
        secondFloor.setBorderPainted(false);
        fifthFloor.setBorderPainted(false);
        secondFloor.setContentAreaFilled(false);
        fifthFloor.setContentAreaFilled(false);

        secondFloor.addActionListener(this);
        fifthFloor.addActionListener(this);

        add(fifthFloor);
        add(secondFloor);

        image1 = new ImageIcon("noonsongee.png").getImage();

        JLabel label2 = new JLabel("강의실을 예약할 명신관 층수를 눌러주세요!");
        label2.setFont(new Font("SanSerif", Font.BOLD, 15));
        label2.setBounds(45,300,400,100);
        label2.setHorizontalAlignment(JLabel.CENTER);
        add(label2);

        //뒤로가기버튼
        ImageIcon back_icon = new ImageIcon("btnBack.png");
        Image back_icon_img = back_icon.getImage();
        Image scale_back_icon = back_icon_img.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        ImageIcon changed_back_btn = new ImageIcon(scale_back_icon);
        btnback = new JButton(changed_back_btn);
        btnback.setBounds(10,20,50,30);
        btnback.setBorderPainted(false);
        btnback.setContentAreaFilled(false);
        btnback.addActionListener(this);
        add(btnback);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image1, 160, 120, 150, 180, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==secondFloor) {
            win.change("2층 강의실 예약 화면으로"); //강의실 예약 화면으로 이동
        }else if(e.getSource()==fifthFloor) {
            win.change("5층 강의실 예약 화면으로"); //분실물 채팅 화면으로 이동
        }
        else if(e.getSource()==btnback) {
            win.change("강의실예약/분실물채팅 선택화면으로"); //다시 강의실예약/분실물채팅 선택화면으로 이동
        }
    }
}