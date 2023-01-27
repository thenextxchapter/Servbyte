package com.nony.servbyte.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByUserEmail(String userEmail);
	List<Order> findByServiceProviderId(Integer serviceProvider_id);
	List<Order> findByOrderTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
