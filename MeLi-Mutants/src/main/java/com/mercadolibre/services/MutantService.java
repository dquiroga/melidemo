package com.mercadolibre.services;

import com.mercadolibre.exceptions.InvalidMatrixException;

public interface MutantService {
	public boolean isMutant(String[] dna) throws InvalidMatrixException;
}
