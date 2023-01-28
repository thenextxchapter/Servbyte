package com.nony.servbyte.orderItem;

import java.util.List;
import java.util.Optional;

import com.nony.servbyte.exception.MealNotFoundException;
import com.nony.servbyte.exception.OrderItemNotFoundException;
import com.nony.servbyte.exception.OrderNotFoundException;
import com.nony.servbyte.meal.Meal;
import com.nony.servbyte.meal.MealRepository;
import com.nony.servbyte.order.Order;
import com.nony.servbyte.order.OrderRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

	private final OrderItemRepository orderItemRepo;

	private final OrderRepository orderRepo;

	private final MealRepository mealRepo;

	public OrderItemService(OrderItemRepository orderItemRepo, OrderRepository orderRepo, MealRepository mealRepo) {
		this.orderItemRepo = orderItemRepo;
		this.orderRepo = orderRepo;
		this.mealRepo = mealRepo;
	}

	public List<OrderItem> listAll() {
		return orderItemRepo.findAll();
	}

	public List<OrderItem> listOrderItemByOrder(Integer orderId) throws OrderNotFoundException {
		Optional<Order> order = orderRepo.findById(orderId);

		if (order.isEmpty()) {
			throw new OrderNotFoundException();
		}
		return orderItemRepo.findByOrderId(orderId);
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

	public List<OrderItem> listOrderItemByMeal(Integer mealId) throws MealNotFoundException {
		Optional<Meal> meal = mealRepo.findById(mealId);

		if (meal.isEmpty()) {
			throw new MealNotFoundException();
		}
		return orderItemRepo.findByMealId(mealId);
	}
}
