package com.lti.poc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.poc.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	Logger log = LoggerFactory.getLogger(PersonController.class);
	private static final String M_QUEUE = "message_queue1";
	Person person= new Person();
	
	@Autowired
	public JmsTemplate jmsTemplate;
	
	@PostMapping("/{pid}")
	public void putPersonData(@PathVariable("pid") Long pid) {
		person.setId(pid);
		log.info("*****sending with convertAndSend() to " + M_QUEUE + " <" + pid + ">");
		log.info("*****Sending a person id info in JMS Queue:{}",pid);
		jmsTemplate.convertAndSend(M_QUEUE,person);
	}
	
}
