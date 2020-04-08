package com.asl.pms.repository;


import com.asl.pms.mymodel.SessionOpLog;
import com.asl.pms.mymodel.SessionPingInfo;
import com.asl.pms.mymodel.WorkSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SessionOpLogRepository extends JpaRepository<SessionOpLog, Long> {


    String query = "SELECT users.id, users.first_name, users.last_name, users.mobile_number, session_op_log.lan, session_op_log.lat, session_op_log.address \r\n" +
            "FROM users \r\n" +
            "INNER JOIN work_session ON work_session.user_id = users.id \r\n" +
            "INNER JOIN session_op_log ON session_op_log.work_session_id = work_session.id  \r\n";

    @Query(value = query, nativeQuery = true)
    List<SessionOpLog> showSessionList();

    List<SessionOpLog> findAllByCreatedBetween(String start, String end);

    List<SessionOpLog> findByWorkSessionOrderByCreatedDesc(WorkSession workSession);

    Page<SessionOpLog> findAll(Pageable pageable);


    String str = "SELECT id AS id, address, created, work_session_id, lan, lat \n"+
            "    FROM session_op_log\n" +
            "    WHERE created BETWEEN :start AND :end \n" +
            "    ORDER BY created DESC";
    @Query(value = str, nativeQuery = true)
    List<SessionOpLog> findByCreatedBetweenOrderByIdDesc(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
