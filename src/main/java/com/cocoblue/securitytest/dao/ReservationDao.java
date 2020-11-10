package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Reservation;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Reservation> rowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);
    private final SimpleJdbcInsert reservationInsertAction;

    public ReservationDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.reservationInsertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("rno");
    }

    public List<Reservation> getAllConfirmedReservation() {
        return jdbc.query(ReservationDaoSqls.SELECT_ALL_CONFIRMED_RESERVATION, new HashMap<String, Object>(), rowMapper);
    }

    public List<Reservation> getAllConfirmedReservationByDoctorNo(long doctorNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("doctorNo", doctorNo);

        return jdbc.query(ReservationDaoSqls.SELECT_ALL_CONFIRMED_RESERVATION_BY_DOCTOR_NO, params, rowMapper);
    }

    public List<Reservation> getAllReservationByCno(long cno) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cno", cno);

        return jdbc.query(ReservationDaoSqls.SELECT_ALL_RESERVATION_BY_CNO, params, rowMapper);
    }

    public Boolean makeReservation(Reservation reservation) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);

        // Insert가 완료되면 true
        return reservationInsertAction.execute(params) > 0;
    }

    public Boolean cancelReservation(long rno) {
        Map<String, Object> map = new HashMap<>();
        map.put("rno", rno);

        // Update가 완료되면 true
        return jdbc.update(ReservationDaoSqls.UPDATE_RESERVATION_CONFIRMED_FALSE, map) > 0;
    }

    // TODO: 다음날부터 7일 중, 주말을 제외한 날짜를 List<String> 형태로 Return 해야함. (Return 타입 변경 예정)
    public List<String> getAvailableDates(long dno) {
        List<String> availableDatesString = new ArrayList<String>();

        return availableDatesString;
    }
}