package com.mercadolibre.app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.domian.Person;
import com.mercadolibre.services.MutantService;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class MercadolibreApplicationTests {

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MutantService mutantService;
	
	@Before
	public void setup() { 	
	}

	@Test
	public void testIsMutant() {
		HttpHeaders headers = new HttpHeaders();
 		headers.setContentType(MediaType.APPLICATION_JSON);
 		String[] body = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
 		HttpEntity<Person> entity = new HttpEntity<Person>(new Person(body),headers);
	
		ResponseEntity<String> response = this.restTemplate.exchange("/mutant/", HttpMethod.POST, entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	@Test
	public void testNonMutant() {
		HttpHeaders headers = new HttpHeaders();
 		headers.setContentType(MediaType.APPLICATION_JSON);
 		String[] body = {"ATGCAT","GCATAG","CTACGT","AGTACG","GAGCTA","TCACTG"};
 		HttpEntity<Person> entity = new HttpEntity<Person>(new Person(body),headers);
	
		ResponseEntity<String> response = this.restTemplate.exchange("/mutant/", HttpMethod.POST, entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
	}
	
	@Test
	public void testIsNotMatrix() {
		HttpHeaders headers = new HttpHeaders();
 		headers.setContentType(MediaType.APPLICATION_JSON);
 		String[] body = {"ATGCAT","GCATAG","CTACT","AGTACG","GAGCTA","TCACTG"};
 		HttpEntity<Person> entity = new HttpEntity<Person>(new Person(body),headers);
	
		ResponseEntity<String> response = this.restTemplate.exchange("/mutant/", HttpMethod.POST, entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
	}
}
