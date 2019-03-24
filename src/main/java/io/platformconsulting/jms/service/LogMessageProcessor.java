package io.platformconsulting.jms.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class LogMessageProcessor implements MessageProcessor {

    private static final Log logger = LogFactory.getLog(LogMessageProcessor.class);

    @Override
    public void processMessage(String payload) throws Exception {
        logger.info(payload);
    }
}
