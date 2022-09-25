package com.heartcooking.tracesallergen;

import com.heartcooking.Allergen;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor
@Setter
@Table(name = "traces_allergens", schema = "heartcooking")
public class TracesAllergen implements Allergen, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column()
	private String name;
}