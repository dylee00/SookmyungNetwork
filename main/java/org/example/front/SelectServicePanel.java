package org.example.front;

import org.example.Main;
//import org.example.reservation.LoginMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectServicePanel extends JPanel implements ActionListener {
//    private LoginMenuController lmc = new LoginMenuController();

    private JButton reservation, chatting;
    private Main win;

    public SelectServicePanel(Main win) {
        this.win = win;
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        reservation = new JButton(new ImageIcon("btnReservation.png"));
        chatting = new JButton(new ImageIcon("btnChatting.png"));

        reservation.setBounds(90,100,300,200);
        chatting.setBounds(90,300,300,200);
        reservation.setBorderPainted(false);
        chatting.setBorderPainted(false);
        reservation.setContentAreaFilled(false);
        chatting.setContentAreaFilled(false);

        reservation.addActionListener(this);
        chatting.addActionListener(this);

        add(chatting);
        add(reservation);

        JLabel label1 = new JLabel("사용하실 서비스를 선택해주세요!");
        label1.setFont(new Font("SanSerif", Font.BOLD, 20));
        label1.setBounds(40,50,400,100);
        label1.setHorizontalAlignment(JLabel.CENTER);
        add(label1);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reservation) {
            win.change("층 선택 화면으로"); //강의실 층 화면으로 이동
        }else if(e.getSource()==chatting) {
            win.change("분실물 채팅 화면으로"); //분실물 채팅 화면으로 이동
        }
    }
}