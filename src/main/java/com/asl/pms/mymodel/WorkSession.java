package com.asl.pms.mymodel;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@Table(name = "work_session")
public class WorkSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    private boolean active;

    private LocalDateTime modified;

    @Column(insertable = true, updatable = false)
    private Date created;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User customer;

    @Column(columnDefinition = "text")
    private String closingComment;
}
