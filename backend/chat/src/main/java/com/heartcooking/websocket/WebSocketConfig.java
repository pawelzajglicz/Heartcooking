package com.heartcooking.websocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartcooking.conversation.ConversationServiceImpl;
import com.heartcooking.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@AllArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer  {

	ConversationServiceImpl conversationService;
	ObjectMapper objectMapper;
	TicketService ticketService;
	WebSocketPoolHandler webSocketPoolHandler;

	@Bean
	public com.heartcooking.websocket.WebSocketHandler myMessageHandler() {
		return new com.heartcooking.websocket.WebSocketHandler(conversationService, objectMapper, ticketService, webSocketPoolHandler);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myMessageHandler(), "/api/messaging").setAllowedOrigins("*");
	}

}
