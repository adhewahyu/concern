package com.data.civil.configuration;

import com.data.civil.service.PreparePersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
public class CivilConfiguration {

    @Autowired
    private PreparePersonService preparePersonService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSetDummyData(){
        log.info("doSetDummyData [start]");
        preparePersonService.execute();
        log.info("doSetDummyData [end]");
    }

}
