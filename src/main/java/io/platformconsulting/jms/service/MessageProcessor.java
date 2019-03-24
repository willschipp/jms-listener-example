package io.platformconsulting.jms.service;

public interface MessageProcessor {

    void processMessage(String payload) throws Exception;
}
