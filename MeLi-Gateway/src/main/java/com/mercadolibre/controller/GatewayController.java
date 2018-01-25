package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class GatewayController {
	
	@Value("${service.mutant.api.url}")
	private String mutantUrl;
	
	@Value("${service.stats.api.url}")
	private String statsUrl;
	
	@HystrixCommand(fallbackMethod = "defaultCreateError")
 	@RequestMapping(value = "/mutant/", method = RequestMethod.POST)
    ResponseEntity<String> create(@RequestBody String request) {
 		String url = mutantUrl+"mutant/";
 		RestTemplate restTemplate = new RestTemplate();
 		HttpHeaders headers = new HttpHeaders();
 		headers.setContentType(MediaType.APPLICATION_JSON); 
 		HttpEntity<String> entity = new HttpEntity<String>(request,headers);
 		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
 		
 		try {
 			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
 		}catch( HttpClientErrorException e ) {
 			ResponseEntity.status(e.getStatusCode());
 		}
 		return response;
     }
 	
 	@HystrixCommand(fallbackMethod = "defaultError")
 	@RequestMapping(value = "/stats", method = RequestMethod.GET)
    ResponseEntity<String> getStats() {
 		String url = statsUrl+"stats";
 		RestTemplate restTemplate = new RestTemplate();
 		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
 		
 		try {
 			response = restTemplate.getForEntity(url, String.class);
 		}catch( HttpClientErrorException e ) {
 			//Logg 
 			ResponseEntity.status(e.getStatusCode());
 		}
 		return response;
    }
 	
 	ResponseEntity<String> defaultStatsError() {
        return  ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("{ \"status\": \"error\", \"message\":\"UPS!!! Por algun motivo no podemos darte las estadisticas que necesitas. Podrias intentar mas tarde?\"}" );
    }
 	
 	ResponseEntity<String> defaultCreateError(@RequestBody String request) {
        return  ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("{ \"status\": \"error\", \"message\":\"UPS!!! Por algun motivo no podemos procesar el ADN que nos enviaste. Podrias intentar mas tarde?\"}" );
    }
}
