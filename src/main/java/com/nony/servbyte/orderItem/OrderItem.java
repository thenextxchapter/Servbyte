package com.nony.servbyte.orderItem;

import java.util.Objects;

import com.nony.servbyte.meal.Meal;
import com.nony.servbyte.order.Order;
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
@Table(name = "order_items")
@Getter
@Setter
@ToString
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "meal_id")
	private Meal meal;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "price")
	private float price;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		OrderItem orderItem = (OrderItem) o;
		return id != null && Objects.equals(id, orderItem.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
