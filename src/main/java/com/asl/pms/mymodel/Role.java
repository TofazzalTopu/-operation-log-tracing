package com.asl.pms.mymodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;


    public Role() {
    }



    public Role(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
