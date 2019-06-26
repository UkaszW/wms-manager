package com.lodz.p.edu.iap.lab.wmsmanager.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
public class DataSync {

    // "0 0 * * * *" = the top of every hour of every day
    // "*/10 * * * * *" = every ten seconds
    @Scheduled(cron = "*/10 * * * * *")
    private void sync() {
        log.info("Date: " + Instant.now());
    }
}
