package com.mercadolibre.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.domian.Person;
import com.mercadolibre.dtos.Message;
import com.mercadolibre.dtos.PersonDTO;
import com.mercadolibre.exceptions.InvalidMatrixException;
import com.mercadolibre.repository.PersonRepository;
import com.mercadolibre.services.MessageSender;
import com.mercadolibre.services.MutantService;

@RestController
public class MutantController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
 
	@Autowired
	MutantService mutantService;
	
	@Autowired
	MessageSender<Message> messageSender;
	
	@Autowired
	PersonRepository repo;
	
	@RequestMapping(value = "/mutant/", method = RequestMethod.POST)
    ResponseEntity<Object> create(@RequestBody PersonDTO person) {
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		boolean isMutant = false;
		try {
			if( (isMutant = mutantService.isMutant(person.getDna())) ) {
				response = (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.OK).body(null);
			} 
			//Persist DNA
			repo.save(new Person(person.getDna()));
			
			//Send Notificacion to stats services
			messageSender.sendMessage(new Message(true,isMutant));
		} catch (InvalidMatrixException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
			
		return response;
    }
	
	@RequestMapping(value = "/mutant", method = RequestMethod.GET)
    ResponseEntity<Object> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
   }
	
}
