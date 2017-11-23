package com.trade.web.chat;

import com.trade.domain.chat.AdMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping("/ad-publishing")
	@SendTo("/trade/chat")
	public AdMessage greeting(@Payload AdMessage message) {
		return message;
	}
}
