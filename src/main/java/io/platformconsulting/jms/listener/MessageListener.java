package io.platformconsulting.jms.listener;

import io.platformconsulting.jms.service.MessageProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageListener {

    private static final Log logger = LogFactory.getLog(MessageListener.class);

    @Autowired
    private MessageProcessor messageProcessor;

    @JmsListener(destination = "${spring.activemq.queue.name}",selector = "${spring.activemq.queue.selector}")
    public void processMessage(String payload, @Headers Map<String,Object> headers) throws Exception {
        logger.info("received " + headers);
        messageProcessor.processMessage(payload);
    }

}
