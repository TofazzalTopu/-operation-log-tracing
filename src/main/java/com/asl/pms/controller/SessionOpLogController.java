package com.asl.pms.controller;

import com.asl.pms.mymodel.SessionOpLog;
import com.asl.pms.repository.SessionOpLogRepository;
import com.asl.pms.service.SessionOpLogService;
import com.asl.pms.service.UserService;
import com.asl.pms.utility.GlobalMethod;
import com.asl.pms.utility.StringUtil;
import com.asl.pms.viewmodel.WorkSessionDetailsData;
import com.asl.pms.viewmodel.WorkSessionWiseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/sessionOpLog")
public class SessionOpLogController {

    @Autowired
    GlobalMethod globalMethod;

    @Autowired
    UserService userService;

    @Autowired
    SessionOpLogService sessionOpLogService;

    @Autowired
    SessionOpLogRepository sessionOpLogRepository;


    @RequestMapping(method = RequestMethod.GET, value = {"/opLogForm"})
    public String opLogForm(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
                            @RequestParam(required = false, defaultValue = "50") Integer total,
                            @RequestParam(required = false, defaultValue = "") String range) {


        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        List<WorkSessionWiseLog> workSessionWiseLogList = new ArrayList<>();
        List<WorkSessionDetailsData> opLogList= new ArrayList<>();
        WorkSessionDetailsData workSessionDetailsData= new WorkSessionDetailsData();

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeZone(TimeZone.getTimeZone("GMT"));

            String to = formatter.format(cal1.getTime());
            String today = to + " 23:59:59.999";

            System.out.println("current date: " + today);

            //subtracting days from Date in Java
            cal1.add(Calendar.DATE, -7);
            String before7 = formatter.format(cal1.getTime());
            String before7Day = before7 + " 00:00:00.000";
            System.out.println("date before 7 days : " + before7Day);
            Date fromDay;
            Date toDay;

            if (StringUtil.isEmptyString(range) || range.length() != 21) {
                fromDay = formatter2.parse(before7Day);
                toDay = formatter2.parse(today);
            } else {
                String range1 = range.split("_")[0] + " 00:00:00.000";
                String range2 = range.split("_")[1] + " 23:59:59.999";
                fromDay = formatter2.parse(range1);
                toDay = formatter2.parse(range2);
            }

            workSessionWiseLogList = sessionOpLogService.getAllSessionInfo(fromDay, toDay);

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("workSessionWiseLogList",workSessionWiseLogList);
        return "sessionoplog/paginglist";
    }

}
