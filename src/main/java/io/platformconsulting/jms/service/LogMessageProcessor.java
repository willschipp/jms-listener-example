package io.platformconsulting.jms.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LogMessageProcessor implements MessageProcessor {

    private static final Log logger = LogFactory.getLog(LogMessageProcessor.class);

    private AtomicInteger receivedCounter = new AtomicInteger(0);

    @Override
    public void processMessage(String payload) throws Exception {
        receivedCounter.addAndGet(1);
        logger.info(payload);
    }

    public int counter() {
        logger.info("retrieving");
        return receivedCounter.get();
    }

    public void reset() {
        logger.info("resetting");
        receivedCounter.set(0);
    }
}
