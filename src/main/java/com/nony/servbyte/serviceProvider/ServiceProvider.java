package com.nony.servbyte.serviceProvider;

import java.util.Objects;

import com.nony.servbyte.city.City;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "service_provider")
@Getter
@Setter
@ToString
public class ServiceProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 40, nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Type type;

	@Column(length = 128, nullable = false, unique = true)
	private String email;

	@Column(length = 15, nullable = false, unique = true)
	private String phoneNumber;

	@Column(length = 64)
	private String picture;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		ServiceProvider that = (ServiceProvider) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
