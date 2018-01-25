package com.mercadolibre.services;

import org.springframework.stereotype.Service;

import com.mercadolibre.exceptions.InvalidMatrixException;


@Service
public class MutantServiceImpl implements MutantService {
	
	private int minHits = 4;
	
	private int minOccurences = 2;
	
	/**
	 * Simple position structure
	 * @author Damian
	 */
	private class Position{
		public int axisx;
		public int axisy;
	}

	
	/**
	 * isMutant
	 * This method analyzes if the DNA belongs to mutant person or not
	 * @param dna 
	 * @return boolean
	 * @throws InvalidMatrixException 
	 */
	@Override
	public boolean isMutant(String[] dna) throws InvalidMatrixException {
		char[] row;
		Position pos = new Position();
		int success_count=0;

		//we go through each row
		for( int i=0; i< ( dna.length ) && success_count<2; i++) {
			row = dna[i].toCharArray();
			
			//For each row we go through each element
			for( int j=0; j< row.length && success_count<2; j++) {
				//Set current position 
				pos.axisx = j; pos.axisy = i;
				
				//Process horizontal possibilities
				if( horizontal(dna, row[j], pos) ) success_count++;
				
				//Process vertical possibilities
				if ( success_count<2 && vertical(dna, row[j], pos) ) success_count++;
	
				//Process oblique possibilities
				if ( success_count<2 && oblique(dna, row[j], pos)) success_count++;
				
			}
		
		}
		
		return success_count >= minOccurences;
	}
	
	
	/**
	 * HORIZONTAL PATH
	 * @param dna
	 * @param value
	 * @param pos
	 * @return boolean
	 */
	private boolean horizontal(String[] dna, char value, Position pos) {
		int i = pos.axisx+1;
		int hits = 1;
		char[] horizontal = dna[pos.axisy].toCharArray();
		
		while( i < dna.length && horizontal[i] == value && hits < minHits) {
			i++; hits++;
		}
		
		return hits >= minHits ;
	}
	
	/**
	 * VERTICAL PATH
	 * @param dna
	 * @param value
	 * @param pos
	 * @return boolean
	 * @throws InvalidMatrixException 
	 */
	private boolean vertical(String[] dna, char value, Position pos) throws InvalidMatrixException {
		int i = pos.axisy+1 ;
		int hits = 1;
		
		if( i >= dna.length )
			return false;
		
		if( dna[i].toCharArray().length <= pos.axisx ) 
			throw new InvalidMatrixException("The DNA given is not correct");
		
		while( i < dna.length && dna[i].toCharArray()[pos.axisx] == value 
				&& hits < minHits) {
			i++; hits++;
		}
		
		return hits >= minHits;
	}
	
	/**
	 * OBLIQUE PATH
	 * @param dna
	 * @param value
	 * @param pos
	 * @return boolean
	 */
	private boolean oblique(String[] dna, char value, Position pos) {
		pos.axisy+=1;
		pos.axisx+=1;
		int hits = 1;
				
		while( (pos.axisx < dna.length && pos.axisy < dna.length) 
				&& dna[pos.axisx].toCharArray()[pos.axisy] == value
				&& hits < minHits) {
			pos.axisx++; pos.axisy++; hits++;
		}
		
		return hits >= minHits;
	}
}
