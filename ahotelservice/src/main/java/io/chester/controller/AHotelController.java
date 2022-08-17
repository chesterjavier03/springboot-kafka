package io.chester.controller;

import io.chester.service.AHotelService;
import io.chester.utility.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chesterjavier.
 */
@RestController
@RequestMapping(path = "/api")
@Slf4j
public class AHotelController {

	@Autowired
	private AHotelService aHotelService;

	@PostMapping(path = "/importer")
	public ResponseEntity importer(@RequestParam("messages") String messages) {
		try {
			log.info("[AHotelController] - [importer] Processing reservation messages... [{}]", messages);
			aHotelService.sendMessage(messages);
			return new ResponseEntity("Message received!", HttpStatus.OK);
		} catch (Exception e) {
			log.error("[AHotelController] ERROR [{}]", e.getLocalizedMessage());
			return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@MessageMapping("/messages")
	@SendTo(Constants.WEBSOCKET_TOPIC)
	public String broadcastToConsumerGroup(@Payload String messages) {
		log.info("[AHotelController] - [broadcastToConsumerGroup] start broadcasting message [{}] to topic [{}]", messages, Constants.WEBSOCKET_TOPIC);
		return messages;
	}
}
