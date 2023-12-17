package org.example.front;

import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener{
    private JButton button;
    private Font f1;
    private Main win;

    public StartPanel(Main win) {
        this.win = win;

        setLayout(new BorderLayout());
        f1 = new Font("SanSerif", Font.BOLD, 25); // 폰트 설정
        ImageIcon sookmyung = new ImageIcon("sookmyunglogo.png");
        Image slogo = sookmyung.getImage();

        Image changeImage1 = slogo.getScaledInstance(400, 400, Image.SCALE_SMOOTH); //이미지를 버튼의 크기에 맞춤
        ImageIcon changeIcon1 = new ImageIcon(changeImage1);

        //다음 화면으로 넘어가기 위한 버튼
        button = new JButton("명신관 강의실 예약과 채팅 서비스", changeIcon1);
        button .setVerticalTextPosition(JButton.BOTTOM);  // 텍스트 아래로
        button .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데




        button.setPreferredSize(new Dimension(0, 650));
        //button.setForeground(Color.white);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBackground(new Color(255, 255, 255));
        button.setFont(f1);
        button.addActionListener(this); //이벤트 대기a
        add(button, BorderLayout.SOUTH);
    }

    public void paintComponent(Graphics g) {
        Dimension d = getSize();
        //키오스크 첫화면 디자인
        //ImageIcon sookmyung = new ImageIcon("sookmyunglogo.png"); //숙대 로고
        //g.drawImage(sookmyung.getImage(), 120, 200, 400, 400, null);

    }
    //이벤트 처리
    @Override
    public void actionPerformed(ActionEvent e) {
        win.change("로그인/회원가입 선택 화면으로"); //패널을 다음 패널로 전환함
    }
}