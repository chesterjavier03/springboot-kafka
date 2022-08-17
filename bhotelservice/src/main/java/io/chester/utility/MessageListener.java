package io.chester.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by chesterjavier.
 */
@Component
@Slf4j
public class MessageListener {

	@KafkaListener(topics = Constants.TOPIC)
	public void listen(String message) {
		log.info("[MessageListener] - Listening to topic [{}] with message [{}]", Constants.TOPIC, message);
	}
}
