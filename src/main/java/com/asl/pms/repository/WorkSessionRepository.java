package com.asl.pms.repository;

import com.asl.pms.mymodel.SessionOpLog;
import com.asl.pms.mymodel.WorkSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface WorkSessionRepository extends JpaRepository<WorkSession, Long> {


    String str = "SELECT id AS id, user_id, active, modified, created, customer_id, closing_comment\n"+
            "    FROM work_session\n" +
            "    WHERE created => :start" +
            "    AND created  <= :end \n" +
            "    ORDER BY created DESC";
    @Query(value = str, nativeQuery = true)
    List<WorkSession> findByCreatedIsGreaterThanEqualAndCreatedIsLessThanEqualOrderByCreatedDesc(@Param("start") Date start, @Param("end") Date end);


//    List<WorkSession> findByCreatedGreaterThanEqualAndCreatedLessThanEqualOrderByCreatedDesc(Date start, Date end);

    @Query(value = "from WorkSession where created BETWEEN :start AND :end order by created desc")
    List<WorkSession> findAllByCreatedBetweenOrderByCreatedDesc(@Param("start") Date start, @Param("end") Date end);

}
