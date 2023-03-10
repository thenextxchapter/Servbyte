package com.nony.servbyte.serviceProvider.meal;

import com.nony.servbyte.serviceProvider.ServiceProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meal")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 40, nullable = false)
	private String name;

	@Column
	private String description;

	@Column(length = 64)
	private String picture;

	@Column(nullable = false)
	private Double price;

	@Column
	private Integer prepTime;

	@ManyToOne
	@JoinColumn(name = "service_provider_id")
	private ServiceProvider serviceProvider;
}
