package com.heartcooking;

import com.heartcooking.conversation.ConversationServiceImpl;
import com.heartcooking.conversation.LoadConversationDto;
import com.heartcooking.message.MessageService;
import com.heartcooking.message.Message;
import com.heartcooking.ticket.TicketResponse;
import com.heartcooking.ticket.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

	private final TicketServiceImpl ticketService;
	private final ConversationServiceImpl conversationService;
	private final MessageService messageService;

	@GetMapping("/tickets")
	public ResponseEntity<TicketResponse> getChatTicket() {

		return ResponseEntity.ok(ticketService.generateTicket());
	}

	@GetMapping("/conversations")
	public ResponseEntity<LoadConversationDto> getConversationWithUser(@RequestParam Long interlocutorId) {

		return ResponseEntity.ok(conversationService.loadConversationWithUser(interlocutorId));
	}

	@GetMapping("/conversations/{conversationId}/messages")
	public ResponseEntity<List<Message>> getConversationMessages(@PathVariable Long conversationId,
																 @RequestParam Long from,
																 @RequestParam Long to) {

		return ResponseEntity.ok(messageService.getMessages(conversationId, from, to));
	}
}
