package com.asl.pms.service;

import com.asl.pms.mymodel.SessionPingInfo;
import com.asl.pms.repository.SessionPingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SessionPingInfoServices {

    @Autowired
    SessionPingInfoRepository sessionPingInfoRepository;

    public List<SessionPingInfo> getAll() {
        List<SessionPingInfo> infoList = sessionPingInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return infoList;
    }

    public List<SessionPingInfo> findAllByCreatedBetween(Date fromDate, Date toDate) {
        List<SessionPingInfo> infoList = sessionPingInfoRepository.findAllByCreatedBetween(fromDate, toDate);
        return infoList;
    }


}
