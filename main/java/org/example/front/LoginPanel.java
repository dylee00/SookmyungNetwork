package org.example.front;

import org.example.Main;
import org.example.controller.UserController;
import org.example.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    private JButton login;

    private JButton btnback;
    private Main win;

    private UserController userController;  // 추가
    private User user;  // 추가
    private JTextField text_id;  // 클래스 변수로 변경

    public LoginPanel(Main win) {
        this.win = win;
        this.userController = new UserController();
        this.text_id = new JTextField();
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        JLabel label = new JLabel("명신에 잇송");
        label.setFont(new Font("SanSerif", Font.BOLD, 29));
        label.setBounds(150,30,200,100);
        label.setHorizontalAlignment(JLabel.CENTER);

        JLabel label1 = new JLabel("학번: ");
        label1.setFont(new Font("SanSerif", Font.BOLD, 20));
        label1.setBounds(135,200,80,30);

        JLabel label2 = new JLabel("로그인을 위해 학번을 입력해주세요!");
        label2.setFont(new Font("SanSerif", Font.BOLD, 15));
        label2.setBounds(45,90,400,100);
        label2.setHorizontalAlignment(JLabel.CENTER);

        //로그인 입력창
        text_id = new JTextField();
        text_id.setBounds(190,200,150,30);

        //로그인실행버튼
        login = new JButton(new ImageIcon("btnCheckLogIn.png"));
        login.setBounds(115,400,270,180);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.addActionListener(this);

        add(login);   add(label);
        add(label1);  add(label2);
        add(text_id);

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
        if(e.getSource()==btnback) {
            win.change("로그인/회원가입 선택 화면으로"); //다시 로그인/회원가입 선택 화면으로 이동
        }else if(e.getSource()==login) {
            String id = text_id.getText();  // 학번 가져오기
            userController.userLogin(id);// 로그인 시도
            User user = userController.getUser(id); //유저 정보 객체에 저장
            win.currentUser = user;
            win.change("강의실예약/분실물채팅 선택화면으로"); //로그인 후, 강의실예약/분실물채팅 선택화면으로 바로 이동
        }

    }
}