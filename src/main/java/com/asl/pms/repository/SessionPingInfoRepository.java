package com.asl.pms.repository;

import com.asl.pms.mymodel.SessionPingInfo;
import com.asl.pms.mymodel.WorkSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SessionPingInfoRepository extends JpaRepository<SessionPingInfo, Long> {

    String str = "SELECT id AS id, address, created, work_session_id, lan, lat \n"+
            "    FROM session_ping_info\n" +
            "    WHERE created BETWEEN :start AND :end \n" +
            "    ORDER BY created DESC";
    @Query(value = str, nativeQuery = true)
    List<SessionPingInfo> findByCreatedBetweenOrderByIdDesc(@Param("start") Date start, @Param("end") Date end);


//    List<SessionPingInfo> findByCreatedBetweenOrderByIdDesc(Date start, Date end);

    @Query(value = "from SessionPingInfo where created BETWEEN :start AND :end order by created desc")
    List<SessionPingInfo> findAllByCreatedBetween(@Param("start") Date start, @Param("end") Date end);

}
