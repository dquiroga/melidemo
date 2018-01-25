package com.mercadolibre.dtos;

public class Message {
	private boolean person;
	private boolean mutant;
	
	public Message(boolean person, boolean mutant) {
		super();
		this.person = person;
		this.mutant = mutant;
	}
	public boolean isPerson() {
		return person;
	}
	public void setPerson(boolean person) {
		this.person = person;
	}
	public boolean isMutant() {
		return mutant;
	}
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	
	
	
}
