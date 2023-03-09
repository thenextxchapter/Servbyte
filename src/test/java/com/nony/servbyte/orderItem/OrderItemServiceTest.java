/*
package com.nony.servbyte.orderItem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.nony.servbyte.exception.OrderItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTest {

	@Mock
	private OrderItemRepository orderItemRepo;

	@InjectMocks
	private OrderItemService orderItemService;

	@Test
	void listAll_ShouldReturnAllCities() {
		List<OrderItem> orderItems = Arrays.asList(
				new OrderItem(),
				new OrderItem()
		);

		when(orderItemRepo.findAll()).thenReturn(orderItems);

		List<OrderItem> result = orderItemService.listAll();

		assertThat(result).isEqualTo(orderItems);
	}

	@Test
	void getAllOrderItems_ShouldReturnOrderItems_WhenGivenValidOrderId() throws OrderItemNotFoundException {
		Integer orderId = 1;
		List<OrderItem> expectedOrderItems = Arrays.asList(
				new OrderItem(),
				new OrderItem()
		);

		when(orderItemRepo.findByOrderId(orderId)).thenReturn(expectedOrderItems);

		List<OrderItem> actualOrderItems = orderItemService.listOrderItemByOrder(orderId);

		assertEquals(expectedOrderItems, actualOrderItems);
	}

	@Test
	void getAllOrderItems_ShouldThrowOrderItemNotFoundException_WhenNoOrderItemsFoundForGivenOrder() {
		Integer orderId = 1;

		when(orderItemRepo.findByOrderId(orderId))
				.thenThrow(NoSuchElementException.class);

		assertThatThrownBy(() ->
				orderItemService.listOrderItemByOrder(orderId))
				.isInstanceOf(OrderItemNotFoundException.class);
	}

	@Test
	void getOrderItemById_ShouldReturnOrderItem_WhenOrderItemExists() throws OrderItemNotFoundException {
		Integer id = 1;
		OrderItem orderItem = new OrderItem();

		when(orderItemRepo.findById(id)).thenReturn(Optional.of(orderItem));

		OrderItem result = orderItemService.getOrderItemById(id);

		assertThat(result).isEqualTo(orderItem);
	}

	@Test
	void getOrderItemById_ShouldThrowOrderItemNotFoundException_WhenOrderItemDoesNotExist() {
		when(orderItemRepo.findById(1))
				.thenReturn(Optional.empty());

		assertThatThrownBy(() ->
				orderItemService.getOrderItemById(1))
				.isInstanceOf(OrderItemNotFoundException.class);
	}

	@Test
	void saveOrderItem_shouldReturnSavedOrderItem_whenGivenValidOrderItem() {
		OrderItem orderItem = new OrderItem();
		OrderItem expectedSavedOrderItem = new OrderItem();

		when(orderItemRepo.save(orderItem)).thenReturn(expectedSavedOrderItem);

		OrderItem actualSavedOrderItem = orderItemService.saveOrderItem(orderItem);

		assertEquals(expectedSavedOrderItem, actualSavedOrderItem);
	}

	@Test
	void deleteOrderItem_withValidId_shouldDeleteOrderItem() throws OrderItemNotFoundException {
		Integer id = 1;

		orderItemService.deleteOrderItem(id);

		verify(orderItemRepo).deleteById(id);
	}

	@Test
	void deleteOrderItem_withInvalidId_shouldThrowOrderItemNotFoundException() {
		Integer id = 1;

		doThrow(new EmptyResultDataAccessException(1))
				.when(orderItemRepo).deleteById(id);

		assertThrows(OrderItemNotFoundException.class,
				() -> orderItemService.deleteOrderItem(id));
	}
}
*/
