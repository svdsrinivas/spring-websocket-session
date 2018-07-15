package com.mia.wskafka.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class ReceiverConfig {

	@Bean
	  public Map<String, Object> consumerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    // list of host:port pairs used for establishing the initial connections to the Kafka cluster
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    // allows a pool of processes to divide the work of consuming and processing records
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "greet");
	    // automatically reset the offset to the earliest offset
	    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

	    return props;
	  }

	  @Bean
	  public ConsumerFactory<String, Offer> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),new JsonDeserializer<>(Offer.class));
	  }

	  @Bean
	  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Offer>> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, Offer> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	  }

	  @Bean
	  public KafkaConsumer receiver() {
	    return new KafkaConsumer();
	  }
}
