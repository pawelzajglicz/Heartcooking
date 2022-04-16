package com.heartcooking.conversation;

import com.heartcooking.message.Message;

public interface ConversationService {
	LoadConversationDto loadConversationWithUser(Long interlocutorId);

	Conversation getConversationById(Long conversationId);

	void handleNewMessage(Message message);

	void sendMessageToConversationParticipants(Message message);
}
