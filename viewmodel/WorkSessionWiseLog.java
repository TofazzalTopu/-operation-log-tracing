package com.asl.pms.viewmodel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
//@Entity
public class WorkSessionWiseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String customerId;

//    private LocationDetails startLocation;
//    private LocationDetails endLocation;

    private Float startLan;
    private Float endLan;
    private Float startLat;
    private Float endLat;
    private String startAddress;
    private String endAddress;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String closingComment;
    private String duration;
    private String startPostCode;
    private String endPostCode;
}

