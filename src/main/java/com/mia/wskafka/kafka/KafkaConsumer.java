package com.mia.wskafka.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.mia.wskafka.config.SocketHandler;

public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	MessageStorage storage;
	
	Map<String,WebSocketSession> sessions = SocketHandler.sessions;
	
	@KafkaListener(topics="topic")
    public void processMessage(Offer content) throws Exception {
		WebSocketSession session = sessions.get(content.getStoken());
		System.out.println("received session = '{}'"+session);
		session.sendMessage(new TextMessage(new Gson().toJson(content,Offer.class)));
    }
}
