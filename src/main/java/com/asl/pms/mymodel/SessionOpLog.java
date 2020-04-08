package com.asl.pms.mymodel;


import com.asl.pms.utility.SessionOpLogType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class SessionOpLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne(targetEntity = WorkSession.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "work_session_id")
    private WorkSession workSession;

    Float lat;

    Float lan;

    String address;

    @Column(columnDefinition = "enum('OPEN','CLOSE')")
    @Enumerated(EnumType.STRING)
    private SessionOpLogType type;

    @Column(insertable = true, updatable = false)
    LocalDateTime created;

    @Column(name = "post_code")
    private String postCode;

}


