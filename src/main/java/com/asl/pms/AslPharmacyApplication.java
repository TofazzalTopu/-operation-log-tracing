package com.asl.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class AslPharmacyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AslPharmacyApplication.class, args);
    }

    @PostConstruct
    void started() {
        System.out.println("Timezone- UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
