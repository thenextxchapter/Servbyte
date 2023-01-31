package com.nony.servbyte.payment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentRepositoryTest {

	@Mock
	private PaymentRepository paymentRepository;

	@Test
	void findByOrderId_ReturnsPayment() {
		Integer orderId = 1;
		Payment payment = new Payment();

		when(paymentRepository.findByOrderId(orderId)).thenReturn(payment);

		Payment result = paymentRepository.findByOrderId(orderId);

		assertNotNull(result);
		assertEquals(payment, result);
	}

	@Test
	void findByOrderId_ThrowsException() {
		Integer orderId = 1;

		when(paymentRepository.findByOrderId(orderId)).thenThrow(NoSuchElementException.class);

		assertThrows(NoSuchElementException.class, () -> paymentRepository.findByOrderId(orderId));
	}
}
