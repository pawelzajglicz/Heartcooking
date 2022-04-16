package com.heartcooking;

import com.heartcooking.auth.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts", schema = "heartcooking")
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "user_id", nullable = false)
	private User firstUser;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User secondUser;

	@Column(name = "connected_at")
	private Instant connectedAt;

}