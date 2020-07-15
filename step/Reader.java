package com.lti.poc.step;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.lti.poc.model.Person;
import com.lti.poc.service.PersonServiceImpl;


public class Reader implements ItemReader<Person>,StepExecutionListener {
	Logger log = LoggerFactory.getLogger(Reader.class);
	@Autowired
	private PersonServiceImpl PersonServiceImpl;
	
	@Autowired(required = false)
	private ExecutionContext executionContext;
	
	private Long personId;
	Person person=null;
	private boolean batchJobState = false;
	@Override 
	public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) 
	  { 
		  executionContext =stepExecution.getExecutionContext();
		  personId=executionContext.getLong("person.id");
		  log.info("*****Job Parameters from ExecutionContext {}",personId);
	  }
	 

	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Optional<Person> optional = PersonServiceImpl.findById(personId);
		 if(!batchJobState){
			 if(optional.isPresent()){
			 	person = optional.get();
				batchJobState=true;
				log.info("*****Person info with  person id {} exists in DB : {}",personId,person);
	            return person;
		        } else {
		        	log.info("*****No records found with  person id {} in DB",personId);
		        	return null;
		        }
		 	}
	        return null;
	}	
		
		
		
		
	@Override 
	 public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) 
	 { 
		 	return stepExecution.getExitStatus();
	 }
}
