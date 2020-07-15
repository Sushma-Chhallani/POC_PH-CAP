package com.lti.poc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.listener.JobParameterExecutionContextCopyListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lti.poc.model.Person;
import com.lti.poc.step.Reader;
import com.lti.poc.step.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration  extends JobExecutionListenerSupport {
	
	Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	@Autowired
    private JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    
    @Bean
	public Job readDataFromDBJob() {
    	return jobBuilderFactory.get("readDataFromDBJob")
				.incrementer(new RunIdIncrementer()).listener(this)
				.flow(stepToReadDataFromDBJob()).end().build();
	}

	@Bean
	public Step stepToReadDataFromDBJob() {
		return stepBuilderFactory.get("stepToReadDataFromDBJob").<Person, Person> chunk(1)
				.reader(reader()).writer(writer()).listener(new JobParameterExecutionContextCopyListener())
				.build();
		
	}
	@Bean
	public ItemReader<Person> reader() {
        return new Reader();
    }
	@Bean
    public ItemWriter<Person> writer() {
        return new Writer();
    }

   	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("*****BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}
	
	
}