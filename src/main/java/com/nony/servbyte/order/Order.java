package com.nony.servbyte.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.nony.servbyte.Status;
import com.nony.servbyte.orderItem.OrderItem;
import com.nony.servbyte.serviceProvider.ServiceProvider;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "service_provider_id")
	private ServiceProvider serviceProvider;

	@Column(name = "user_email")
	private String userEmail;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<OrderItem> items;

	@Column(name = "total_price")
	private float totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Column(name = "order_time")
	private LocalDateTime orderTime;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Order order = (Order) o;
		return id != null && Objects.equals(id, order.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
