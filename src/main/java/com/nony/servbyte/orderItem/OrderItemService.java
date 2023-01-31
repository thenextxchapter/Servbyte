package com.nony.servbyte.orderItem;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.servbyte.exception.OrderItemNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

	private final OrderItemRepository orderItemRepo;

	public OrderItemService(OrderItemRepository orderItemRepo) {
		this.orderItemRepo = orderItemRepo;
	}

	public List<OrderItem> listAll() {
		return orderItemRepo.findAll();
	}

	public List<OrderItem> listOrderItemByOrder(Integer orderId) throws OrderItemNotFoundException {
		try {
			return orderItemRepo.findByOrderId(orderId);
		} catch (NoSuchElementException exception) {
			throw new OrderItemNotFoundException();
		}
	}

	public OrderItem getOrderItemById(Integer id) throws OrderItemNotFoundException {
		return orderItemRepo.findById(id).orElseThrow(OrderItemNotFoundException::new);
	}

	public OrderItem saveOrderItem(OrderItem orderItem) {
		return orderItemRepo.save(orderItem);
	}

	public void deleteOrderItem(Integer id) throws OrderItemNotFoundException {
		try {
			orderItemRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new OrderItemNotFoundException();
		}
	}
}
