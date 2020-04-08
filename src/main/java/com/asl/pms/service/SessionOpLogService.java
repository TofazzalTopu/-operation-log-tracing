package com.asl.pms.service;

import com.asl.pms.mymodel.SessionOpLog;
import com.asl.pms.mymodel.WorkSession;
import com.asl.pms.repository.SessionOpLogRepository;
import com.asl.pms.repository.WorkSessionRepository;
import com.asl.pms.utility.SessionOpLogType;
import com.asl.pms.viewmodel.DateWiseComparator;
import com.asl.pms.viewmodel.WorkSessionDetailsData;
import com.asl.pms.viewmodel.WorkSessionWiseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SessionOpLogService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SessionOpLogRepository sessionOpLogRepository;

    @Autowired
    WorkSessionRepository workSessionRepository;

    private static RestTemplate restTemplate = new RestTemplate();
    //    private static final String baseURL = "http://43.243.206.171:8181/lin/work-session/details/";
    private static final String baseURL = "http://13.250.189.66/lin/work-session/details/";

    public WorkSessionDetailsData getAll(Date fromDay, Date toDay) {
        String userName = "das.bikash.asl@gmail.com";
        String password = "1234";
        ResponseEntity<WorkSessionDetailsData> result = null;
        WorkSessionDetailsData workSessionDetailsData = new WorkSessionDetailsData();
        List<WorkSessionWiseLog> sessionWiseLogs = new ArrayList<>();

        try {
            restTemplate.getInterceptors().add(
                    new BasicAuthorizationInterceptor(userName, password));

            result = restTemplate.exchange(baseURL, HttpMethod.GET, null, WorkSessionDetailsData.class);
            workSessionDetailsData = result.getBody();

           /* workSessionDetailsData.getData().forEach(c -> {
                if (((c.getStartTime()).after(fromDay) && (c.getEndTime()).before(toDay))) {
                    sessionWiseLogs.add(c);
                }
            });
            workSessionDetailsData.getData().stream().filter(s ->
                    (s.getStartTime()).after(fromDay) && (s.getStartTime()).before(toDay)).collect(Collectors.toList());
            workSessionDetailsData.getData().forEach(c ->
                    {
                        if (c.getStartTime().after(fromDay) && c.getEndTime().before(toDay)) {
                            System.out.println(c.getId() + "  " + c.getStartTime());
                        }
                    }

            );*/
//            workSessionDetailsData = (WorkSessionDetailsData) sessionWiseLogs;
            return workSessionDetailsData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workSessionDetailsData;
    }


    public List<WorkSessionWiseLog> getAllSessionInfo(Date fromDate, Date toDate) {
        List<WorkSession> workSessionList = new ArrayList<>();
        List<SessionOpLog> logArrayList = new ArrayList<>();
        List<WorkSessionWiseLog> sessionWiseLogArrayList = new ArrayList<>();
//        List<WorkSessionWiseLog> sessionWiseLogArrayList = workSessionWiseLogRepository.findAllByOrderByStartTimeDesc();
        try {

            workSessionList = workSessionRepository.findAllByCreatedBetweenOrderByCreatedDesc(fromDate, toDate);

            if (workSessionList.size() > 0) {
                for (WorkSession session : workSessionList) {
                    logArrayList = sessionOpLogRepository.findByWorkSessionOrderByCreatedDesc(session);
                    WorkSessionWiseLog workSessionWiseLog = new WorkSessionWiseLog();

                    for (int i = 0; i <= logArrayList.size(); i++) {
                        for (SessionOpLog log : logArrayList) {

                            if (log.getType().equals(SessionOpLogType.OPEN)) {
                                workSessionWiseLog.setId(log.getId());
                                workSessionWiseLog.setUserId(log.getWorkSession().getCustomer().getId());
                                workSessionWiseLog.setFirstName(log.getWorkSession().getCustomer().getFirstName());
                                workSessionWiseLog.setLastName(log.getWorkSession().getCustomer().getLastName());
                                workSessionWiseLog.setMobileNumber(log.getWorkSession().getCustomer().getMobileNumber());
                                workSessionWiseLog.setCustomerId(log.getWorkSession().getCustomer().getId());

                                workSessionWiseLog.setStartPostCode(log.getPostCode());
                                workSessionWiseLog.setStartAddress(log.getAddress());
                                workSessionWiseLog.setStartTime(log.getCreated());
                                workSessionWiseLog.setStartLan(log.getLan());
                                workSessionWiseLog.setClosingComment(log.getWorkSession().getClosingComment());
                            } else {
                                workSessionWiseLog.setEndPostCode(log.getPostCode());
                                workSessionWiseLog.setEndAddress(log.getAddress());
                                workSessionWiseLog.setEndTime(log.getCreated());
                                workSessionWiseLog.setEndLan(log.getLan());
                                workSessionWiseLog.setEndLat(log.getLat());
                            }
                        }
                    }

                    if (workSessionWiseLog.getStartTime() != null && workSessionWiseLog.getEndTime() != null) {
                        long diffInSeconds = java.time.Duration.between(workSessionWiseLog.getStartTime(), workSessionWiseLog.getEndTime()).getSeconds();
                        long diffInMilli = java.time.Duration.between(workSessionWiseLog.getStartTime(), workSessionWiseLog.getEndTime()).toMillis();
                        long diffInMinutes = java.time.Duration.between(workSessionWiseLog.getStartTime(), workSessionWiseLog.getEndTime()).toMinutes();
                        long diffInHours = java.time.Duration.between(workSessionWiseLog.getStartTime(), workSessionWiseLog.getEndTime()).toHours();
                        String duration = diffInHours % 60 + ":" + diffInMinutes % 60 + ":" + diffInSeconds % 60;
                        workSessionWiseLog.setDuration(duration);
                    }

                    sessionWiseLogArrayList.add(workSessionWiseLog);
                }

                Comparator<WorkSessionWiseLog> cmp = Collections.reverseOrder(new DateWiseComparator());
                Collections.sort(sessionWiseLogArrayList, cmp);
                sessionWiseLogArrayList = sessionWiseLogArrayList.stream().sorted(Comparator.comparingLong(WorkSessionWiseLog::getId)).collect(Collectors.toList());
            }
            return sessionWiseLogArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return sessionWiseLogArrayList.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        return sessionWiseLogArrayList;
//        return sessionWiseLogArrayList.stream().sorted(Comparator.comparing(WorkSessionWiseLog::getId)).collect(Collectors.toList());
    }

    public List<SessionOpLog> findAllByCreatedBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        List<SessionOpLog> opLogList = sessionOpLogRepository.findByCreatedBetweenOrderByIdDesc(fromDate, toDate);
        return opLogList;
    }
}
