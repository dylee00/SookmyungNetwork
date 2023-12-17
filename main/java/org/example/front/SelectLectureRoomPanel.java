package org.example.front;
import org.example.Main;
import org.example.chatting.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLectureRoomPanel extends JPanel implements ActionListener {

    private JButton secondFloor, fifthFloor, chatting2, chatting5;
    private Main win;
    private Image image1;
    private JButton btnback; //뒤로가기 버튼
    ChatClient chatTest = new ChatClient();//채팅클라이언트 객체 생성

    public SelectLectureRoomPanel(Main win) {
        this.win = win;
        setLayout(null);
        setBackground(new Color(255, 255, 255));



        //나중에 필요함
        //secondFloor.addActionListener(this);
        //fifthFloor.addActionListener(this);

        JLabel label1 = new JLabel("분실물을 찾을 명신관 층수를 눌러주세요!");
        label1.setFont(new Font("SanSerif", Font.BOLD, 20));
        label1.setBounds(40,50,400,100);
        label1.setHorizontalAlignment(JLabel.CENTER);
        add(label1);

        chatting2 = new JButton(new ImageIcon("btnSecondFloor.png"));
        chatting2.setBounds(140,100,200,200);
        chatting2.setBorderPainted(false);
        chatting2.setContentAreaFilled(false);
        chatting2.addActionListener(this);
        add(chatting2);

        chatting5 = new JButton(new ImageIcon("btnFifthFloor.png"));
        chatting5.setBounds(140,320,200,200);
        chatting5.setBorderPainted(false);
        chatting5.setContentAreaFilled(false);
        chatting5.addActionListener(this);
        add(chatting5);

//        JLabel label2 = new JLabel("강의실을 예약할 명신관 층수를 눌러주세요!");
//        label2.setFont(new Font("SanSerif", Font.BOLD, 15));
//        label2.setBounds(5,290,400,100);
//        label2.setHorizontalAlignment(JLabel.CENTER);
//        add(label2);

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
    public void actionPerformed(ActionEvent e) {
        //아직없음
        if(e.getSource()==btnback) {
            win.change("강의실예약/분실물채팅 선택화면으로"); //다시 로그인/회원가입 선택 화면으로 이동
        }
        else if(e.getSource()==chatting2) {
            String[] args = {"192.168.45.129", "5410"};
            chatTest.main(args);
        }
        else if(e.getSource()==chatting5) {
            String[] args = {"192.168.45.129", "5410"};
            chatTest.main(args);
        }
    }
}
