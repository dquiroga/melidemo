package com.mercadolibre.services;

public interface MessageSender<T> {
	public void sendMessage(T message);
}
