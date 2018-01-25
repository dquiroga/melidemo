package com.mercadolibre.dtos;

public class Message {
	private boolean person;
	private boolean mutant;
	
	public boolean isPerson() {
		return person;
	}
	public boolean isMutant() {
		return mutant;
	}
	public void setPerson(boolean person) {
		this.person = person;
	}
	public boolean getMutant() {
		return mutant;
	}
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	
}
