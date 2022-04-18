package com.heartcooking.product;

import com.heartcooking.photo.Photo;
import com.heartcooking.tracesallergen.TracesAllergen;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "products", schema = "heartcooking")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column()
	private String name;

	@Column()
	private String description;

	@Column()
	private Double carbohydrates;

	@Column()
	private Double fat;

	@Column()
	private Double fiber;

	@Column()
	private Double kilocalories;

	@Column()
	private Double protein;

	@Column()
	private Double salt;

	@Column(name = "saturated_fat")
	private Double saturatedFat;

	@Column()
	private Double sugar;

	@Column(name = "is_allergen")
	private Boolean isAllergen;

	@Column(name = "is_vegan")
	private Boolean isVegan;

	@OneToMany(mappedBy = "product")
	private Set<Photo> photos;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "products_traces_allergens",
			schema = "heartcooking",
			joinColumns = @JoinColumn(name = "traces_allergen_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<TracesAllergen> tracesAllergens;
}