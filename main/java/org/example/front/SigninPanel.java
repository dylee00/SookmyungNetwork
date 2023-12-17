package org.example.front;

import org.example.Main;
import org.example.controller.UserController;
import org.example.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigninPanel extends JPanel implements ActionListener {
    private JButton signin;
    private JButton btnback;
    private Main win;

    private UserController userController;  // 추가
    private JTextField text_id;  // 클래스 변수로 변경
    private JTextField text_name;  // 클래스 변수로 변경


    public SigninPanel(Main win) {
        this.win = win;
        this.userController = new UserController();
//        text_id = new JTextField();  // 수정
//        text_name = new JTextField();  // 수정
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        JLabel label = new JLabel("명신에 잇송");
        label.setFont(new Font("SanSerif", Font.BOLD, 29));
        label.setBounds(150,30,200,100);
        label.setHorizontalAlignment(JLabel.CENTER);

        JLabel label2 = new JLabel("로그인을 위해 학번과 이름을 입력해주세요!");
        label2.setFont(new Font("SanSerif", Font.BOLD, 15));
        label2.setBounds(45,90,400,100);
        label2.setHorizontalAlignment(JLabel.CENTER);

        JLabel label1 = new JLabel("학번: ");
        label1.setFont(new Font("SanSerif", Font.BOLD, 20));
        label1.setBounds(135,200,80,30);

        //학번입력창
        text_id = new JTextField();
        text_id.setBounds(190,200,150,30);

        JLabel label3 = new JLabel("이름: ");
        label3.setFont(new Font("SanSerif", Font.BOLD, 20));
        label3.setBounds(135,250,80,30);

        //이름입력창
        text_name = new JTextField();
        text_name.setBounds(190, 250, 150, 30);

        signin = new JButton(new ImageIcon("btnCheckSignIn.png"));
        signin.setBounds(115,400,270,180);
        signin.setBorderPainted(false);
        signin.setContentAreaFilled(false);
        signin.addActionListener(this);

        add(signin);   add(label);
        add(label1);   add(label2);  add(label3);
        add(text_id);  add(text_name);

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
        }else if(e.getSource()==signin) {
            User newUser = new User(text_id.getText(), text_name.getText());
            userController.userJoin(newUser);  // 회원가입 정보 저장
            win.change("로그인 화면으로"); //회원가입후, 로그인 화면으로 바로 이동
        }
    }
}
