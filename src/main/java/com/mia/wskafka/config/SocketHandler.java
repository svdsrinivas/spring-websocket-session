package com.mia.wskafka.config;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.mia.wskafka.kafka.KafkaProducer;
import com.mia.wskafka.kafka.MessageStorage;
import com.mia.wskafka.kafka.Offer;

public class SocketHandler extends TextWebSocketHandler implements WebSocketHandler{
	
	@Autowired
	KafkaProducer producer;
	
	@Autowired
	MessageStorage storage;

	public static Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
		Offer staff = new Gson().fromJson(message.getPayload(), Offer.class);
		producer.send(staff);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String stoken = (String) session.getAttributes().get("stoken");
		sessions.put(stoken,session);
	}
	
	 @Override
	 public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
	     sessions.remove(session);
	 }

}
