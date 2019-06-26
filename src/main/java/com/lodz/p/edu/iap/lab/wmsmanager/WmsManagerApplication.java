package com.lodz.p.edu.iap.lab.wmsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WmsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsManagerApplication.class, args);
    }

}
