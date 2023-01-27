package com.nony.servbyte.orderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	List<OrderItem> findByOrderId(Integer orderId);
	List<OrderItem> findByMealId(Integer mealId);
}
