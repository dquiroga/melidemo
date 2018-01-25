package com.mercadolibre.services;

import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.domain.MutantStats;
import com.mercadolibre.dtos.Message;
import com.mercadolibre.repository.MutantStatsRepository;

import org.slf4j.Logger;


@Service
public class MutantStatsServiceImpl implements MutantStatsService{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MutantStatsRepository repository;
	
	@Override
	public MutantStats processMutantMessage(Message msg) {
		try { 
			MutantStats item;
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			
			Long human = new Long( Boolean.compare( msg.isPerson(), false) );
			Long mutant = new Long( Boolean.compare( msg.isMutant(), false) );
			
			ArrayList<MutantStats> stats = (ArrayList<MutantStats>) repository.findByDate(today);
			
			if(stats.isEmpty()) {
				item = new MutantStats();
				item.setCountHumanDna(human);
				item.setCountMutantDna(mutant);
				item.setDate(today);
			}else {
				item = stats.get(0);
				item.setCountHumanDna( item.getCountHumanDna() + human);
				item.setCountMutantDna( item.getCountMutantDna() + mutant);
			}
			
			return repository.save(item);
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}

}
