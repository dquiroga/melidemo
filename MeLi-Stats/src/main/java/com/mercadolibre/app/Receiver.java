package com.mercadolibre.app;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.domain.MutantStats;
import com.mercadolibre.dtos.Message;
import com.mercadolibre.services.MutantStatsService;

@Component
public class Receiver {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MutantStatsService statsService;
	
    private CountDownLatch latch = new CountDownLatch(1);
    
    private ObjectMapper mapper = new ObjectMapper();
    
    
    public void receiveMessage(String message) {
    		try {
			Message received = mapper.readValue(message, Message.class);
			MutantStats stats = statsService.processMutantMessage(received);
			latch.countDown();
		} catch (Exception e) {
			log.error(e.getMessage());
		}   
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}