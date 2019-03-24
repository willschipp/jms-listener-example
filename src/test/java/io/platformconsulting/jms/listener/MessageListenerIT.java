package io.platformconsulting.jms.listener;

import io.platformconsulting.jms.service.LogMessageProcessor;
import io.platformconsulting.jms.service.MessageProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageListenerIT {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MessageProcessor messageProcessor;

    @Before
    public void before() {
        ((LogMessageProcessor) messageProcessor).reset();
    }

    @Test
    public void processMessage() throws Exception {
        //baseline
        assertTrue(((LogMessageProcessor) messageProcessor).counter() <= 0);
        //send a message that matches the filter
        jmsTemplate.convertAndSend("hello world",message -> {
            message.setStringProperty("customheader","hiya");
            return message;
        });
        //check that it was received
        Thread.sleep(2 * 1000);//wait 2 seconds
        //check the processor
        assertTrue(((LogMessageProcessor) messageProcessor).counter() > 0);
    }

    @Test
    public void dontProcessMessage() throws Exception {
        //send a message that doesn't match the filter
        //baseline
        assertTrue(((LogMessageProcessor) messageProcessor).counter() <= 0);
        //send a message that matches the filter
        jmsTemplate.convertAndSend("hello world");
        //check that it was received
        Thread.sleep(2 * 1000);//wait 2 seconds
        //check the processor
        assertTrue(((LogMessageProcessor) messageProcessor).counter() == 0);
    }
}