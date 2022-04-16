package com.data.civil.route;

import com.data.civil.route.processor.SendCommLogProcessor;
import com.data.civil.util.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendCommLogRoute extends RouteBuilder {

    private final SendCommLogProcessor sendCommLogProcessor;

    @Override
    public void configure() {
        log.info("SendCommLogRoute is up and running");
        from("activemq:"+ Constants.MQ_SEND_COMMLOG).process(sendCommLogProcessor);
    }

}
