package com.heartcooking.conversation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heartcooking.auth.models.User;
import com.heartcooking.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
public class LoadConversationDto {

	private Long conversationId;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Set<User> participants;
	private List<Message> messages;
	private Long totalMessages;
}
