package com.nony.servbyte.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.nony.servbyte.exception.OrderNotFoundException;
import com.nony.servbyte.exception.RestaurantNotFoundException;
import com.nony.servbyte.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderService orderService;

	@Test
	void listAll_ShouldReturnAllCities() {
		List<Order> orders = Arrays.asList(
				new Order(),
				new Order()
		);

		when(orderRepository.findAll()).thenReturn(orders);

		List<Order> result = orderService.listAll();

		assertThat(result).isEqualTo(orders);
	}

	@Test
	void findById_ShouldReturnOrder_WhenOrderExists() throws OrderNotFoundException {
		Integer id = 1;
		Order order = new Order();

		when(orderRepository.findById(id)).thenReturn(Optional.of(order));

		Order result = orderService.getOrderById(id);

		assertThat(result).isEqualTo(order);
	}

	@Test
	void findById_ShouldThrowOrderNotFoundException_WhenOrderDoesNotExist() {
		when(orderRepository.findById(1))
				.thenReturn(Optional.empty());

		assertThatThrownBy(() ->
				orderService.getOrderById(1))
				.isInstanceOf(OrderNotFoundException.class);
	}

	@Test
	void saveOrder_shouldReturnSavedOrder_WhenGivenValidOrder() {
		Order order = new Order();
		Order expectedSavedOrder = new Order();

		when(orderRepository.save(order)).thenReturn(expectedSavedOrder);

		Order actualOrder = orderService.saveOrder(order);

		assertEquals(expectedSavedOrder, actualOrder);
	}

	@Test
	void deleteOrder_WithValidId_ShouldDeleteOrder() throws OrderNotFoundException {
		Integer id = 1;

		orderService.deleteOrder(id);

		/*
		* verify is a method from the Mockito library that can be used
		* to verify the behavior of the mock objects
		* */
		verify(orderRepository).deleteById(id);
	}

	@Test
	void deleteOrder_WithInvalidId_ShouldThrowOrderNotFoundException() {
		Integer id = 1;
		/*
		* doThrow is a method from Mockito that is used to configure a behavior
		* for the mock object to throw an exception when a certain method is called
		* */
		doThrow(new EmptyResultDataAccessException(1)).when(orderRepository).deleteById(id);

		assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder(id));
	}

	@Test
	void getAllUserOrders_ShouldReturnOrders_WhenGivenValidUserEmail()
			throws UserNotFoundException, OrderNotFoundException {
		String email = "email@email.com";
		List<Order> expectedOrders = Arrays.asList(
				new Order(),
				new Order()
		);

		when(orderRepository.findByUserEmail(email)).thenReturn(expectedOrders);

		List<Order> actualOrders = orderService.getAllUserOrders(email);

		assertEquals(expectedOrders, actualOrders);
	}

	@Test
	void getAllUserOrders_ShouldThrowOrderNotFoundException_whenNoOrdersFoundForGivenUserEmail() {
		String email = "email@email.com";

		when(orderRepository.findByUserEmail(email))
				.thenReturn(Collections.emptyList());

		assertThrows(OrderNotFoundException.class,
				() -> orderService.getAllUserOrders(email));
	}

	@Test
	void getAllRestaurantOrders_ShouldThrowUserNotFoundException_WhenUserEmailIsNotValid() {
		when(orderRepository.findByUserEmail("email"))
				.thenThrow(NoSuchElementException.class);

		assertThatThrownBy(() ->
				orderService.getAllUserOrders("email"))
				.isInstanceOf(UserNotFoundException.class);
	}

	@Test
	public void getAllRestaurantOrders_Successful()
			throws OrderNotFoundException, RestaurantNotFoundException {
		List<Order> orders = Arrays.asList(
				new Order(),
				new Order()
		);
		when(orderRepository.findByServiceProviderId(1)).thenReturn(orders);

		List<Order> result = orderService.getAllRestaurantOrders(1);

		assertNotNull(result);
		assertEquals(2, result.size());
		verify(orderRepository, times(1)).findByServiceProviderId(1);
	}

	@Test
	public void getAllRestaurantOrders_OrderNotFoundException() {
		when(orderRepository.findByServiceProviderId(1))
				.thenReturn(new ArrayList<>());

		Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			orderService.getAllRestaurantOrders(1);
		});

		assertEquals("Apparently, this order does not exist", exception.getMessage());
		verify(orderRepository, times(1))
				.findByServiceProviderId(1);
	}

	@Test
	public void getAllRestaurantOrders_RestaurantNotFoundException() {
		when(orderRepository.findByServiceProviderId(1))
				.thenThrow(NoSuchElementException.class);

		Exception exception = assertThrows(RestaurantNotFoundException.class, () -> {
			orderService.getAllRestaurantOrders(1);
		});

		assertEquals("This restaurant does not currently exist", exception.getMessage());
		verify(orderRepository, times(1))
				.findByServiceProviderId(1);
	}
}
