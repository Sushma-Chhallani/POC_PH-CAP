package com.lti.poc.repository;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lti.poc.model.Person;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
	@Autowired
	private PersonRepository personRepository;
	
	 @Test
	    public void whenFindingPersonById_thenCorrect() {
		 Person person = personRepository.findById(1L).orElse(null);
			    assertThat(person.getFirstName()).isEqualTo("John");
			
	 	Person person2 = personRepository.findById(2L).orElse(null);
	 	 assertThat(person2.getLastName()).isEqualTo("Smith2");
	    }
	 
	 
	 @Test(expected=NullPointerException.class)
	    public void whenFindingPersonById_NotPresent() {
		 Person person = personRepository.findById(9L).orElse(null);
			    assertThat(person.getFirstName()).isEqualTo("John");
			
	 	
	    }
}





	

