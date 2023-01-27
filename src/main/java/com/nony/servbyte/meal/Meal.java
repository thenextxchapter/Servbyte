package com.nony.servbyte.meal;

import java.util.Objects;

import com.nony.servbyte.serviceProvider.ServiceProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Table(name = "meal")
@Getter
@Setter
@ToString
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
	private float price;

	@Column
	private Integer prepTime;

	@ManyToOne
	@JoinColumn(name = "service_provider_id")
	private ServiceProvider serviceProvider;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Meal meal = (Meal) o;
		return id != null && Objects.equals(id, meal.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
