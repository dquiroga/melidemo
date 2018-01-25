package com.mercadolibre.services;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public abstract class MessageSenderImpl<T> implements MessageSender<T>{
	protected final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public MessageSenderImpl(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	    
	
	@Override
	public abstract void sendMessage(T message);
}
