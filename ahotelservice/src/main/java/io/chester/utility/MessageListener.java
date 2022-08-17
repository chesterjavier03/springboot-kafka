package io.chester.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by chesterjavier.
 */
@Component
@Slf4j
public class MessageListener {

	@Autowired
	private SimpMessagingTemplate template;

	@KafkaListener(topics = Constants.TOPIC)
	public void listen(String message) {
		log.info("[MessageListener] - Listening to topic [{}] and send to websocket group [{}] with message [{}]", Constants.TOPIC, Constants.WEBSOCKET_TOPIC, message);
		template.convertAndSend(Constants.WEBSOCKET_TOPIC, message);
	}
}
