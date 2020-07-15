package com.lti.poc.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;


import com.lti.poc.receiver.JmsMessageReceiver;


public class DefaultErrorHandler implements ErrorHandler {
private static Logger log = LoggerFactory.getLogger(JmsMessageReceiver.class);

@Override
	public void handleError(Throwable t) {
	    log.warn("spring jms custom error handling example");
	    log.error(t.getCause().getMessage());
	}
}
