package io.chester.service;

import io.chester.utility.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by chesterjavier.
 */
@Service
@Slf4j
public class AHotelService {

	@Autowired
	private KafkaTemplate template;

	public void sendMessage(String message) {
		log.info("[AHotelService] - Sending message [{}] to topic [{}]", message, Constants.TOPIC);
		template.send(Constants.TOPIC, message);

	}
}
