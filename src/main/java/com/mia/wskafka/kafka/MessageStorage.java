package com.mia.wskafka.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageStorage {
	
	private Map<String,Offer> storage = new HashMap<>();
	
	public void put(Offer msg){
		storage.put(msg.getStoken(),msg);
	}
	
	public Offer get(String stoken) {
		if(storage!=null && !storage.isEmpty()) {
			Offer offer = storage.get(stoken);
			storage.remove(stoken);
			return offer;
		}
		return null;
	}
	
	public void clear(){
		storage.clear();
	}
}
