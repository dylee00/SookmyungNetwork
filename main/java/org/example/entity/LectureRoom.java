package org.example.entity;


public class LectureRoom {
    private int lPk;
    private String lName;
    private String lTime;
    private boolean res;
    private String userId;

    //생성자
    public LectureRoom() {}

    //강의실 예약 관련 객체
    public LectureRoom(String lName, String lTime, String userId) {
        super();
        this.lName = lName;
        this.lTime = lTime;
        this.userId = userId;
    }



    //getter&setter
    public int getlPk() {
        return lPk;
    }

    public void setlPk(int lPk) {
        this.lPk = lPk;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlTime() {
        return lTime;
    }

    public void setlTime(String lTime) {
        this.lTime = lTime;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
