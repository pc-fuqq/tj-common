package com.tj.common.kafka.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * kafka定制化消息发送端
 * @author silongz
 *
 */
public class CustomKafkaProducer {
    
	private Producer<String, String> producer ;
	
	public CustomKafkaProducer(String propertiesFilePrefix){
		producer = ProducerSingleton.getInstance(propertiesFilePrefix) ;
	}
	
	/**
	 * 发送消息，不指定消息key，消息随机到kafka的某一个partition
	 * @param topic
	 * @param msg
	 */
    public void sendMsg(String topic , String msg) {
		ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, msg);
		producer.send(data) ;
		producer.flush();
	}
	
    /**
     * 发送消息，并指定消息key，相同key的消息在同一个partition
     * @param topic
     * @param key
     * @param value
     */
	public void sendMsg(String topic , String key, String value) {
		ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, key , value);
		producer.send(data) ;
		producer.flush();
	}
	
}
