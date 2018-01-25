package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.repository.MutantStatsRepository;
import com.mercadolibre.domain.MutantStats;

@RestController
public class StatsController {
	@Autowired
	MutantStatsRepository repository;
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
    ResponseEntity<MutantStats> getStats() {
		ResponseEntity<MutantStats> response = new ResponseEntity<MutantStats>(HttpStatus.FORBIDDEN);
		MutantStats stats = this.repository.getSummarizationStats();
		if( stats != null)
			response = ResponseEntity.status(HttpStatus.OK).body(stats);
		
		return response;
    }
	
}
