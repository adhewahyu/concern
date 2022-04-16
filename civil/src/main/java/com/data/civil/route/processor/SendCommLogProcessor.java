package com.data.civil.route.processor;

import com.alibaba.fastjson.JSON;
import com.data.civil.model.request.SaveCommLogRequest;
import com.data.civil.service.SaveCommLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendCommLogProcessor implements Processor {

    private final SaveCommLogService saveCommLogService;

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("SendCommLogProcessor.process - receive message");
        String message = exchange.getMessage().getBody().toString();
        saveCommLogService.execute(JSON.parseObject(message, SaveCommLogRequest.class));
    }
}
