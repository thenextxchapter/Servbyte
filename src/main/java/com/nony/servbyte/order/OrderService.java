package com.nony.servbyte.order;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.servbyte.exception.OrderNotFoundException;
import com.nony.servbyte.exception.RestaurantNotFoundException;
import com.nony.servbyte.exception.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderRepository orderRepo;

	public OrderService(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	public List<Order> listAll() {
		return orderRepo.findAll();
	}

	public Order getOrderById(Integer id) throws OrderNotFoundException {
		return orderRepo.findById(id).orElseThrow(OrderNotFoundException::new);
	}

	public Order saveOrder(Order order) {
		return orderRepo.save(order);
	}

	public void deleteOrder(Integer id) throws OrderNotFoundException {
		try {
			orderRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new OrderNotFoundException();
		}
	}

	public List<Order> getAllUserOrders(String userEmail) throws OrderNotFoundException, UserNotFoundException {
		try {
			List<Order> orders =  orderRepo.findByUserEmail(userEmail);

			if (orders.isEmpty()) {
				throw new OrderNotFoundException();
			}
			return orders;
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException();
		}
	}

	public List<Order> getAllRestaurantOrders(Integer serviceProvider_id) throws OrderNotFoundException, RestaurantNotFoundException {
		try {
			List<Order> orders = orderRepo.findByServiceProviderId(serviceProvider_id);

			if (orders.isEmpty()) {
				throw new OrderNotFoundException();
			}
			return orders;
		} catch (NoSuchElementException exception) {
			throw new RestaurantNotFoundException();
		}
	}
}
