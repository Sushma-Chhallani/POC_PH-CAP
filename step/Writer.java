package com.lti.poc.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.lti.poc.model.Person;


public class Writer implements ItemWriter<Person>{
	Logger log = LoggerFactory.getLogger(Writer.class);
 
 @Override
 public void write(List<? extends Person> person) throws Exception {
	 log.info("*****In ItemWriter Class");
 }
 
}