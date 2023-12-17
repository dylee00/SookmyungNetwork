package org.example.controller;

import org.example.entity.LectureRoom;
import org.example.service.ReservationService;

import java.sql.Time;
import java.util.List;

public class ReservationController {
    private ReservationService reservationService = new ReservationService();

    //강의실 예약
    public int reservation(String lName, String lTime, String userId) {
        int result = reservationService.reservation(lName, lTime, userId);
        if (result == 1) {
            System.out.println(lName +"예약 성공");
        }else {
            System.out.println("이미 예약된 강의실입니다");
        }

        return result;
    }

    //강의실 전체 리스트업
    public void resSelectAll() {
        List<LectureRoom> lectureRooms = reservationService.resSelectAll();

        if (lectureRooms.isEmpty()) {
            System.out.println("예약 가능한 강의실이 없습니다");
        } else {
            System.out.println("예약 가능한 강의실 목록");
            for (LectureRoom room : lectureRooms) {
                System.out.println("강의실 이름: " + room.getlName() + ", 시간: " + room.getlTime());
            }
        }
    }

    //2층 강의실 리스트업
    public void resSelect2() {
        List<LectureRoom> lectureRooms2 = reservationService.resSelect2();

        if (lectureRooms2.isEmpty()) {
            System.out.println("예약 가능한 강의실이 없습니다");
        } else {
            System.out.println("예약 가능한 강의실 목록");
            for (LectureRoom room : lectureRooms2) {
                System.out.println("강의실 이름: " + room.getlName() + ", 시간: " + room.getlTime());
            }

        }
    }

    //5층 강의실 리스트업
    public void resSelect5() {
        List<LectureRoom> lectureRooms5 = reservationService.resSelect5();

        if (lectureRooms5.isEmpty()) {
            System.out.println("예약 가능한 강의실이 없습니다");
        } else {
            System.out.println("예약 가능한 강의실 목록");
            for (LectureRoom room : lectureRooms5) {
                System.out.println("강의실 이름: " + room.getlName() + ", 시간: " + room.getlTime());
            }
        }
    }

    //2층 강의실 리스트객체로 출력
    public List<LectureRoom> getResSelect2() {
        List<LectureRoom> lectureRooms2 = reservationService.resSelect2();
        return lectureRooms2;
    }

    //5층 강의실 리스트객체로 출력
    public List<LectureRoom> getResSelect5() {
        List<LectureRoom> lectureRooms5 = reservationService.resSelect5();
        return lectureRooms5;
    }
}
