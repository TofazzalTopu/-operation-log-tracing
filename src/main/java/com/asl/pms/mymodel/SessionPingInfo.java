package com.asl.pms.mymodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "session_ping_info")
public class SessionPingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JsonIgnore
    @ManyToOne(targetEntity = WorkSession.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "work_session_id")
    private WorkSession workSession;

    private Float lat;

    private Float lan;

    private String address;

    @Column(insertable = true, updatable = false)
    private Date created;

    @Column(name = "post_code")
    private String postCode;
}

