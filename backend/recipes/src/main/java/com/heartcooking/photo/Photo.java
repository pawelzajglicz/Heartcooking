package com.heartcooking.photo;

import com.heartcooking.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photos", schema = "heartcooking")
public class Photo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column()
	private String url;

	@Column()
	private String description;

	@Column(name = "added_at")
	private Instant addedAt;

	@Column(name = "is_main")
	private Boolean isMain;

	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
}