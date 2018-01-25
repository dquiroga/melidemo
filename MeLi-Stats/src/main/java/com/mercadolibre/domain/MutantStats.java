package com.mercadolibre.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Temporal;


@Entity
@Table(name="mutant_stats")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MutantStats implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Long countMutantDna;
	
	private Long countHumanDna;
	
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private java.util.Calendar date;
	
	public MutantStats() {
		super();
	}

	public MutantStats(Long countMutantDna, Long countHumanDna) {
		super();
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
	}
	
	public Long getCountMutantDna() {
		return countMutantDna;
	}


	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}


	public Long getCountHumanDna() {
		return countHumanDna;
	}


	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}


	public java.util.Calendar getDate() {
		return date;
	}

	public void setDate(java.util.Calendar date) {
		this.date = date;
	}
	
	
	public BigDecimal getRatio() {
		float result = (float)this.countMutantDna/this.countHumanDna;
		BigDecimal roundfinalPrice = new BigDecimal(result).setScale(2,BigDecimal.ROUND_HALF_UP);
		return roundfinalPrice;
	}
}
