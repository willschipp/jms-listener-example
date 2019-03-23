package io.platformconsulting.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

@EnableJms
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Log logger = LogFactory.getLog(Application.class);

    public static void main(String... args) {
        SpringApplication.run(Application.class,args);
    }

    @JmsListener(destination = "${spring.activemq.queue.name}",selector = "${spring.activemq.queue.selector}")
    public void processMessage(String payload, @Headers Map<String,Object> headers) throws Exception {
        logger.info("message received " + payload);
        logger.info("headers " + headers);
    }

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.convertAndSend("hello world",message -> {
            message.setStringProperty("customheader","hiya");
            return message;
        });
        logger.info("sent a message with a custom header");
        jmsTemplate.convertAndSend("without a selector");
        logger.info("sent a message without a custom header");
    }
}
