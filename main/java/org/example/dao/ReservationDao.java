package org.example.dao;

import org.example.entity.LectureRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.jdbc.JdbcTemplate.close;

public class ReservationDao {
    //강의실 예약
    public int reservation(Connection conn, String lName, String lTime, String userId) {
        String sql = "UPDATE LECTUREROOM SET RES = 1, USER_ID = ? WHERE LNAME = ? AND LTIME = ? AND RES = 0";
        PreparedStatement pstmt = null;
        int result = 0;

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, lName);
            pstmt.setString(3, lTime);
            result = pstmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return result;
    }

    //예약 가능한 강의실 리스트업(2층)(res == 0인 경우만 리스트업 해주면 됨)
    public List<LectureRoom> resSelectAll(Connection conn) {
        String sql = "SELECT LNAME, LTIME FROM LECTUREROOM WHERE RES = 0";
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<LectureRoom> lectureRoom = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while (rset.next()) {
                LectureRoom room = new LectureRoom();
                room.setlName(rset.getString("LNAME"));
                room.setlTime(rset.getString("LTIME"));

                lectureRoom.add(room);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        return lectureRoom;
    }
    //2층 선택
    public List<LectureRoom> resSelect2(Connection conn) {
        String sql = "SELECT LNAME, LTIME FROM LECTUREROOM WHERE RES = 0 AND LFLOOR = 2";
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<LectureRoom> lectureRoom = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while (rset.next()) {
                LectureRoom room = new LectureRoom();
                room.setlName(rset.getString("LNAME"));
                room.setlTime(rset.getString("LTIME"));

                lectureRoom.add(room);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        return lectureRoom; //2층 객체
    }
    //5층 선택
    public List<LectureRoom> resSelect5(Connection conn) {
        String sql = "SELECT LNAME, LTIME FROM LECTUREROOM WHERE RES = 0 AND LFLOOR = 5";
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<LectureRoom> lectureRoom = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while (rset.next()) {
                LectureRoom room = new LectureRoom();
                room.setlName(rset.getString("LNAME"));
                room.setlTime(rset.getString("LTIME"));

                lectureRoom.add(room);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        return lectureRoom;//5층 객체
    }

}
