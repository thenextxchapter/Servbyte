package com.nony.servbyte.payment;

import java.util.Objects;

import com.nony.servbyte.Status;
import com.nony.servbyte.order.Order;
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
@Table(name = "payments")
@Getter
@Setter
@ToString
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "amount")
	private float amount;

	@Column(name = "payment_token")
	private String paymentToken;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Payment payment = (Payment) o;
		return id != null && Objects.equals(id, payment.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
