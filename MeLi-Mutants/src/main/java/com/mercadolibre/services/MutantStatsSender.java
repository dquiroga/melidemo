package com.mercadolibre.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.dtos.Message;

@Service
public class MutantStatsSender extends MessageSenderImpl<Message>{
	@Value("${rabbit.mutant.stats.exchange}")
	private String exchangeName;
	
	@Value("${rabbit.mutant.stats.routing-key}")
	private String routingKey;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public MutantStatsSender(RabbitTemplate rabbitTemplate) {
		super(rabbitTemplate);
	}
	
	@Scheduled(fixedDelay = 3000L)
	@Override
	public void sendMessage(Message message) {
		try {
			String json = mapper.writeValueAsString(message);
			rabbitTemplate.convertAndSend(exchangeName, routingKey, json );	
		}catch ( JsonProcessingException e) {
			e.printStackTrace();
		}	
	}
	

}
