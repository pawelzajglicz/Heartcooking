package com.heartcooking.conversation;

import com.heartcooking.auth.models.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "conversations", schema = "heartcooking")
public class Conversation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_at")
	private Instant createdAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_conversations",
			   schema = "heartcooking",
			   joinColumns = @JoinColumn(name = "conversation_id"),
			   inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<User> participants;

}