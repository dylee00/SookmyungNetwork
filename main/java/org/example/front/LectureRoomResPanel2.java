package org.example.front;

import org.example.Main;
import org.example.controller.ReservationController;
import org.example.entity.LectureRoom;
import java.util.List;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LectureRoomResPanel2 extends JPanel implements ActionListener {

    public void loadReservations2() {
        List<LectureRoom> lectureRooms = reservationController.getResSelect2();

        tableModel.setRowCount(0);  // 테이블 초기화

        for (LectureRoom room : lectureRooms) {
            Object[] row = new Object[3];
            row[0] = "";  // 첫 번째 열의 값 설정
            row[1] = room.getlName();  // 두 번째 열의 값 설정
            row[2] = room.getlTime();  // 세 번째 열의 값 설정
            tableModel.addRow(row);  // 테이블에 행 추가
        }
    }

    private Main win;
    private JButton reserve;
    private JButton btnback; //뒤로가기 버튼
    private DefaultTableModel tableModel;
    private ReservationController reservationController;

    private JTextField text_number = new JTextField();
    private JTextField text_time = new JTextField();


    public LectureRoomResPanel2(Main win) {
        this.win = win;
        this.reservationController = new ReservationController();
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        JLabel label = new JLabel("2층 강의실 리스트");
        label.setFont(new Font("SanSerif", Font.BOLD, 25));
        label.setBounds(150,30,210,100);
        label.setHorizontalAlignment(JLabel.CENTER);

        JLabel label1 = new JLabel("강의실: ");
        label1.setFont(new Font("SanSerif", Font.BOLD, 15));
        label1.setBounds(165,355,80,30);

        //강의실입력창
        text_number = new JTextField();
        text_number.setBounds(220,355,100,28);

        JLabel label2 = new JLabel("시간: ");
        label2.setFont(new Font("SanSerif", Font.BOLD, 15));
        label2.setBounds(178,400,80,30);

        //시간입력창
        text_time = new JTextField();
        text_time.setBounds(220,400,100,28);

        //예약버튼
        reserve = new JButton(new ImageIcon("btnConfirmReserv.png"));
        reserve.setBounds(115,400,270,180);
        reserve.setBorderPainted(false);
        reserve.setContentAreaFilled(false);
        reserve.addActionListener(this);

        //뒤로가기버튼
        ImageIcon back_icon = new ImageIcon("btnBack.png");
        Image back_icon_img = back_icon.getImage();
        Image scale_back_icon = back_icon_img.getScaledInstance(40, 24, Image.SCALE_SMOOTH);
        ImageIcon changed_back_btn = new ImageIcon(scale_back_icon);
        btnback = new JButton(changed_back_btn);
        btnback.setBounds(10,20,40,24);
        btnback.setBorderPainted(false);
        btnback.setContentAreaFilled(false);
        btnback.addActionListener(this);
        add(btnback);

        String[] header = {"", "강의실", "예약가능시간"};
        this.tableModel = new DefaultTableModel(header, 0);
        JTable table = new JTable(tableModel);


        JScrollPane scrollpane = new JScrollPane(table);

        // 테이블 열 크기 조정
        table.getColumn("").setPreferredWidth(25);
        table.getColumn("강의실").setPreferredWidth(200);
        table.getColumn("예약가능시간").setPreferredWidth(200);

        // 테이블 값 가운데 정렬
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel tableColumnModel = table.getColumnModel();
        tableColumnModel.getColumn(0).setCellRenderer(defaultTableCellRenderer);
        tableColumnModel.getColumn(1).setCellRenderer(defaultTableCellRenderer);
        tableColumnModel.getColumn(2).setCellRenderer(defaultTableCellRenderer);

        table.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 14));  //테이블헤더 폰트 설정
        table.setFont(new Font("SanSerif", Font.PLAIN, 13));                 // 셀 폰트 설정

        scrollpane.setBounds(95, 130, 300, 200); // 스크롤 패널의 위치와 크기 설정
        table.setBounds(95, 130, 300, 200); // 테이블의 위치와 크기 설정

        add(scrollpane);
        add(label);
        add(label1);       add(label2);
        add(text_number);  add(text_time);
        add(reserve);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reserve) {
            // 예약 버튼이 클릭되었을 때 실행할 코드
            int result = reservationController.reservation(text_number.getText(), text_time.getText(), win.currentUser.getUserId());
            if (result == 1) {
                reservationController.reservation(text_number.getText(), text_time.getText(), win.currentUser.getUserId());
                // 여기에 새로운 JFrame을 생성하고 보이도록 설정하는 코드를 추가
                JFrame reservationAlertFrame = new ReservationAlert();
                reservationAlertFrame.setTitle("Reservation Alert"); // 타이틀 설정 (원하는 대로 변경)
                reservationAlertFrame.setSize(500, 650); // 적절한 크기로 설정
                reservationAlertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
                reservationAlertFrame.getContentPane().setBackground(Color.WHITE);
                reservationAlertFrame.setVisible(true);
            } else {
                JFrame reservationFailAlertFrame = new ReservationFailAlert();
                reservationFailAlertFrame.setTitle("Reservation Fail Alert"); // 타이틀 설정 (원하는 대로 변경)
                reservationFailAlertFrame.setSize(500, 650); // 적절한 크기로 설정
                reservationFailAlertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
                reservationFailAlertFrame.getContentPane().setBackground(Color.WHITE);
                reservationFailAlertFrame.setVisible(true);
            }

            win.change("강의실예약/분실물채팅 선택화면으로");
        }
        else if(e.getSource()==btnback) {
            win.change("층 선택 화면으로"); //다시 강의실예약/분실물채팅 선택화면으로 이동
        }
    }
}
