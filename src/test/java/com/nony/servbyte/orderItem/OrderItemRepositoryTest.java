package com.nony.servbyte.orderItem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class OrderItemRepositoryTest {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Test
	void findByOrderId() {
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(1);
		assertThat(orderItems).isNotNull();
	}

	@Test
	void findByMealId() {
		List<OrderItem> orderItems = orderItemRepository.findByMealId(1);
		assertThat(orderItems).isNotNull();
	}
}
