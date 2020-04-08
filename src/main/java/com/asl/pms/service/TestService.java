package com.asl.pms.service;

import com.asl.pms.mymodel.SessionOpLog;
import com.asl.pms.repository.SessionOpLogRepository;
import com.asl.pms.repository.WorkSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


//@Service
public class TestService {

    @Autowired
    SessionOpLogRepository sessionOpLogRepository;

    @Autowired
    WorkSessionRepository workSessionRepository;


    public Page<SessionOpLog> findAll(Pageable pageable) {
        Page<SessionOpLog> logList = sessionOpLogRepository.findAll(pageable);

        return logList;
    }


    final private List<SessionOpLog> books = sessionOpLogRepository.findAll();

    /*public Page<SessionOpLog> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SessionOpLog> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<SessionOpLog> bookPage
                = new PageImpl<SessionOpLog>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }*/


}
