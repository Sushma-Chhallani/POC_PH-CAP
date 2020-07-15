package com.lti.poc.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.lti.poc.model.Person;

@Component
public interface PersonService {
	public Optional<Person> findById(Long id);
	 
}
