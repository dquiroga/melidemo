package com.mercadolibre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.mercadolibre.domain.MutantStats;


public interface MutantStatsRepository  extends CrudRepository<MutantStats, Integer>{
	
	List<MutantStats> findByDate(java.util.Calendar date);
	
	@Query("SELECT new com.mercadolibre.domain.MutantStats(SUM(s.countMutantDna) as countMutantDna, SUM(s.countHumanDna) as countHumanDna) FROM MutantStats s ") 
	MutantStats getSummarizationStats();
}
