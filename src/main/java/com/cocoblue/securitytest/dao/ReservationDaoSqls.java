package com.cocoblue.securitytest.dao;

public class ReservationDaoSqls {
    public static String SELECT_ALL_CONFIRMED_RESERVATION = "SELECT * FROM reservation WHERE confirmed = true";
    public static String SELECT_ALL_CONFIRMED_RESERVATION_BY_DOCTOR_NO = "SELECT * FROM reservation WHERE doctor_no = :doctorNo AND confirmed = true";
    public static String SELECT_ALL_RESERVATION_BY_CNO = "SELECT * FROM reservation WHERE cno = :cno";
    public static String UPDATE_RESERVATION_CONFIRMED_FALSE = "UPDATE reservation SET confirmed = false WHERE rno = :rno";
}