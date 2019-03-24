package io.platformconsulting.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(Application.class,args);
    }

    @Autowired
    JmsTemplate jmsTemplate;


    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.convertAndSend("hello world",message -> {
            message.setStringProperty("customheader","hiya");
            return message;
        });
    }
}
