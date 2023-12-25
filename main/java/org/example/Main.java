package org.example;

import org.example.chatting.ChatClient;
import org.example.entity.User;
import org.example.front.*;

import javax.swing.*;

public class Main extends JFrame {
    public StartPanel startPanel = null;
    public LoginPage loginPage = null;//클래스이름 객체이름 (초기설정 null로 setting)
    public LoginPanel loginPanel = null;
    public SigninPanel signinPanel = null;
    public SelectServicePanel selectServicePanel = null;
    public SelectFloorPanel selectFloorPanel = null;
    public LectureRoomResPanel2 lectureRoomResPanel2 = null;
    public LectureRoomResPanel5 lectureRoomResPanel5 = null;
    public static User currentUser = null;

    public SelectChattingRoomPanel selectChattingRoomPanel = null;
//    ChatClient chatTest = new ChatClient();
//
//    public ChatClient chatClient = null;

    //패널 간 전환 메서드
    public void change(String panelName) {

        if (panelName.equals("로그인/회원가입 선택 화면으로")) { //첫 화면에서 주문 방식을 정하는 화면으로의 전환
            getContentPane().removeAll();  //패널을 지움
            getContentPane().add(loginPage);  //패널 올림
            revalidate();
            repaint();
        }
        else if(panelName.equals("회원가입 화면으로")) {  //회원가입 화면으로 이동
            getContentPane().removeAll();  //패널을 지움
            getContentPane().add(signinPanel);  //패널 올림
            revalidate();
            repaint();
        }
        else if(panelName.equals("로그인 화면으로")) {  //로그인 화면으로 이동
            getContentPane().removeAll();  //패널을 지움
            getContentPane().add(loginPanel);  //패널 올림
            revalidate();
            repaint();
        }
        else if(panelName.equals("강의실예약/분실물채팅 선택화면으로")) {  //로그인 화면으로 이동
            getContentPane().removeAll();  //패널을 지움
            getContentPane().add(selectServicePanel);  //패널 올림
            revalidate();
            repaint();
        }
        else if(panelName.equals("층 선택 화면으로")) {  //층 선택 화면으로 이동
            getContentPane().removeAll();  //패널을 지움
            getContentPane().add(selectFloorPanel);  //패널 올림
            revalidate();
            repaint();
        }
//        else if(panelName.equals("2층 강의실 예약 화면으로")) {
//            getContentPane().removeAll();  //패널을 지움
//            getContentPane().add(lectureRoomResPanel2);  //패널 올림
//            revalidate();
//            repaint();
//        }
        else if(panelName.equals("2층 강의실 예약 화면으로")) {
            getContentPane().removeAll();  //패널을 지움
            lectureRoomResPanel2.loadReservations2();  // 강의실 예약 정보 불러오기
            getContentPane().add(lectureRoomResPanel2);  //패널 올림
            revalidate();
            repaint();
        }
        else if(panelName.equals("5층 강의실 예약 화면으로")) {
            getContentPane().removeAll();  //패널을 지움
            lectureRoomResPanel5.loadReservations5();
            getContentPane().add(lectureRoomResPanel5);  //패널 올림
            revalidate();
            repaint();
        }
        else if(panelName.equals("분실물 채팅 화면으로")) {
            getContentPane().removeAll();  //패널을 지움
            getContentPane().add(selectChattingRoomPanel);  //패널 올림
            revalidate();
            repaint();

            //String[] args = {"192.168.45.129", "5410"};
            //String[] args = {"192.168.35.213", "5410"};
            //chatTest.main(args);
        }
        else if(panelName.equals("초기 화면으로")) {
            getContentPane().removeAll();  //패널을 지움
            getContentPane().add(startPanel);  //패널 올림
            revalidate();
            repaint();
        }

    }

    //메인함수
    public static void main(String[] args) {
        Main win = new Main();  //키오스크 객체
        win.setTitle("명신에 잇송");  //창 위에뜨는 이름

        //객체 생성o
        //객체에게 패널부여
        win.startPanel = new StartPanel(win);
        win.loginPage  = new LoginPage(win);
        win.loginPanel = new LoginPanel(win);
        win.signinPanel = new SigninPanel(win);
        win.selectServicePanel = new SelectServicePanel(win);
        win.selectFloorPanel = new SelectFloorPanel(win);
        win.lectureRoomResPanel2 = new LectureRoomResPanel2(win);
        win.lectureRoomResPanel5 = new LectureRoomResPanel5(win);
        win.selectChattingRoomPanel = new SelectChattingRoomPanel(win);

        win.add(win.startPanel);   //startorder로 시작하도록
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(500, 650);
        win.setVisible(true);
        win.setResizable(false);
    }
}
