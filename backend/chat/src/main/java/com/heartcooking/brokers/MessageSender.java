package com.heartcooking.brokers;

import com.heartcooking.message.Message;

public interface MessageSender {
	void send(Message message);
}
