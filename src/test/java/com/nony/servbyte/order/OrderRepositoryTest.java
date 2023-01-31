package com.nony.servbyte.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void findByUserEmail() {
		String email = "email@email.com";
		List<Order> orders = orderRepository.findByUserEmail(email);
		assertThat(orders).isNotNull();
	}

	@Test
	void findByServiceProviderId() {
		List<Order> orders = orderRepository.findByServiceProviderId(1);
		assertThat(orders).isNotNull();
	}
}
