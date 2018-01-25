package com.mercadolibre.services;

import com.mercadolibre.domain.MutantStats;
import com.mercadolibre.dtos.Message;

public interface MutantStatsService {
	public MutantStats processMutantMessage(Message msg);
}
