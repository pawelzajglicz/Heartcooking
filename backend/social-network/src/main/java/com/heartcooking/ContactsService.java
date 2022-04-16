package com.heartcooking;

import com.heartcooking.auth.dto.SimpleUserInfo;

import java.util.Set;

public interface ContactsService {
	Set<SimpleUserInfo> getUserContacts(Long userId);
}
