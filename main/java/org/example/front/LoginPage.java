package org.example.front;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import org.example.Main;
import org.example.entity.User;

import javax.swing.*;

public class LoginPage extends JPanel implements ActionListener {
    Scanner sc  = new Scanner(System.in); // sc는 Scanner 객체로, 사용자로부터 입력을 받는 데 사용
    //private MemberController mc = new MemberController(); 아래와 같이 수정
//    private LoginMenuController lmc = new LoginMenuController();

    private JButton signin, login;
    private Main win;

    public LoginPage(Main win) {
        this.win = win;
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        signin = new JButton(new ImageIcon("btnSignIn.png"));
        login = new JButton(new ImageIcon("btnLogIn.png"));

        signin.setBounds(90,100,300,200);
        login.setBounds(90,300,300,200);

        signin.setBorderPainted(false);
        login.setBorderPainted(false);

        signin.setContentAreaFilled(false);
        login.setContentAreaFilled(false);

        signin.addActionListener(this);
        login.addActionListener(this);

        add(login);
        add(signin);

        JLabel label1 = new JLabel("회원가입/로그인을 선택해주세요!");
        label1.setFont(new Font("SanSerif", Font.BOLD, 20));
        label1.setBounds(40,50,400,100);
        label1.setHorizontalAlignment(JLabel.CENTER);
        add(label1);
    }

//    public void LoginMenu() {
//
//        while(true) {
//            System.out.println("\n *** 로그인 페이지 *** \n");
//            System.out.println("1. 회원가입");
//            System.out.println("2. 로그인");
//            int num1 = Integer.parseInt(sc.nextLine());
//
//            switch (num1) {
//                case 0 : return;
//                case 1 :
//                    // 회원가입을 위한 메서드 호출
//                    lmc.insertMember(inputMember());
//                    break;
//                case 2:
//                    // 로그인을 위한 메서드 호출
//                    lmc.selectAll();
//                    break;
//                default:
//                    System.out.println("잘못된 입력입니다. '1' 또는 '2'를 입력해주세요.");
//            }
//        }
//    }

    // 회원 정보 입력
    public User inputMember() {
        System.out.print("학번 : ");
        String userId = sc.nextLine();
        System.out.print("닉네임 : ");
        String userName = sc.nextLine(); // 다음에 오는 주소 입력을 위해 엔터 제거

        User user = new User(userId, userName);
        return user;
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==signin) {
            win.change("회원가입 화면으로"); //회원가입
        }else if(e.getSource()==login) {
            win.change("로그인 화면으로"); //로그인으로
        }



    }
}