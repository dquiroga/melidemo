package com.mercadolibre.domian;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public class Person {
	@Id
	private String id;
	
	private String[] dna;
	
	public Person() {
	}
	
	public Person(String[] dna) {
		super();
		this.dna = dna;
	}
	
	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

	
}
