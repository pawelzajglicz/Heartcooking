package com.heartcooking;

import com.heartcooking.auth.dto.SimpleUserInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contacts")
public class ContactsController {

	ContactsServiceImpl contactsService;

	@GetMapping("/{userId}")
	public ResponseEntity<Set<SimpleUserInfo>> getUserContacts(@PathVariable Long userId) {

		return ResponseEntity.ok(contactsService.getUserContacts(userId));
	}
}
