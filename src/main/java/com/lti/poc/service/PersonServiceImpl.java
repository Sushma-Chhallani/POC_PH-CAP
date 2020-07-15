package com.lti.poc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lti.poc.model.Person;
import com.lti.poc.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepository personRepository;
	@Cacheable(value="persons")     
	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);	
	}

	
}
