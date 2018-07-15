package com.mia.wskafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String, Offer> kafkaTemplate;
	
	String kafkaTopic = "topic";
	
	public void send(Offer data) {
	    kafkaTemplate.send(kafkaTopic, data);
	}
}
