package org.example.service;

import org.example.dao.ReservationDao;
import org.example.entity.LectureRoom;
import org.example.jdbc.JdbcTemplate;

import java.sql.Connection;
import java.sql.Time;
import java.util.List;

import static org.example.jdbc.JdbcTemplate.*;

public class ReservationService {
    private ReservationDao reservationDao = new ReservationDao();

    //강의실 예약
    public int reservation(String lName, String lTime, String userId) {
        int result = -10;

        Connection conn = JdbcTemplate.getConnection();
        result = reservationDao.reservation(conn, lName,lTime,userId);
        if (result >0) {
            commit(conn);

        }else{
            rollback(conn);

        }
        close(conn);

        return result;
    }

    //강의실 리스트업
    public List<LectureRoom> resSelectAll() {
        Connection conn = JdbcTemplate.getConnection();

        List<LectureRoom> lectureRooms = reservationDao.resSelectAll(conn);

        close(conn);

        return lectureRooms;
    }

    //2층 강의실 리스트업
    public List<LectureRoom> resSelect2() {
        Connection conn = JdbcTemplate.getConnection();

        List<LectureRoom> lectureRooms2 = reservationDao.resSelect2(conn);

        close(conn);

        return lectureRooms2;
    }

    //5층 강의실 리스트업
    public List<LectureRoom> resSelect5() {
        Connection conn = JdbcTemplate.getConnection();

        List<LectureRoom> lectureRooms2 = reservationDao.resSelect5(conn);

        close(conn);

        return lectureRooms2;
    }



}
