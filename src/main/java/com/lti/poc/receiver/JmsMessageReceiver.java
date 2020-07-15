package com.lti.poc.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.lti.poc.model.Person;

@Component
public class JmsMessageReceiver {
	
	private static final String M_QUEUE = "message_queue1";
	Logger log = LoggerFactory.getLogger(JmsMessageReceiver.class);
	 @Autowired
	 JobLauncher jobLauncher;
	 
	 @Autowired
	 Job readDataFromDBJob;
	    
	    
	@JmsListener(destination=M_QUEUE)
	public void receiveMessage(Person person) throws Exception{
		log.info("*****Message Received by @JmsListener with person id :{}",person.getId());
		
		 JobParametersBuilder builder = new JobParametersBuilder();
		 builder.addLong("person.id", person.getId());
		 builder.addLong("time",System.currentTimeMillis());
		 
		 log.info("*****Batch job has been invoked");
		 jobLauncher.run(readDataFromDBJob, builder.toJobParameters());
		}

}
