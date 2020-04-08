package com.asl.pms.mymodel;


import lombok.Data;

import javax.persistence.Id;

@Data
//@Entity
public class Book {
    @Id
    private int id;
    private String name;

    // standard constructor, setters and getters
}
