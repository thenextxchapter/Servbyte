package com.nony.servbyte.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.nony.servbyte.Status;
import com.nony.servbyte.exception.PaymentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
	@Mock
	private PaymentRepository paymentRepository;

	@InjectMocks
	private PaymentService paymentService;

	@Test
	void createPayment_shouldReturnSavedPayment_whenGivenValidPayment() {
		Payment payment = new Payment();
		Payment expectedSavedPayment = new Payment();

		when(paymentRepository.save(payment)).thenReturn(expectedSavedPayment);

		Payment actualPayment = paymentService.createPayment(payment);

		assertEquals(expectedSavedPayment, actualPayment);
	}

	@Test
	void findById_shouldReturnPayment_whenPaymentExists() throws PaymentNotFoundException {
		Integer id = 1;
		Payment payment = new Payment();

		when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

		Payment result = paymentService.getPaymentById(id);

		assertThat(result).isEqualTo(payment);
	}

	@Test
	void findById_shouldThrowPaymentNotFoundException_whenPaymentDoesNotExist() {
		when(paymentRepository.findById(1))
				.thenReturn(Optional.empty());

		assertThatThrownBy(() ->
				paymentService.getPaymentById(1))
				.isInstanceOf(PaymentNotFoundException.class);
	}

	@Test
	void getAllOrderPayments_shouldReturnPayment_whenGivenValidOrder() throws PaymentNotFoundException {
		Integer id = 1;
		Payment expectedPayment = new Payment();

		when(paymentRepository.findByOrderId(id)).thenReturn(expectedPayment);

		Payment actualPayment = paymentService.getPaymentByOrder(id);

		assertEquals(expectedPayment, actualPayment);
	}

	@Test
	void getAllOrderPayments_shouldThrowPaymentNotFoundException_whenNoPaymentFoundForGivenOrder() {
		Integer id = 1;

		when(paymentRepository.findByOrderId(id)).thenThrow(NoSuchElementException.class);

		assertThrows(PaymentNotFoundException.class,
				() -> paymentService.getPaymentByOrder(id));
	}

	@Test
	void updatePayment_success() throws PaymentNotFoundException {
		Payment payment = new Payment(1, 100.54, "token", Status.APPROVED);
		Payment paymentToUpdate = new Payment(1, 200.0, "updated-token", Status.PENDING);

		when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));
		when(paymentRepository.save(paymentToUpdate)).thenReturn(paymentToUpdate);

		Payment updatedPayment = paymentService.updatePayment(1, paymentToUpdate);

		assertEquals(1, updatedPayment.getId());
		assertEquals(200.0, updatedPayment.getAmount(), 0.001);
		assertEquals("updated-token", updatedPayment.getPaymentToken());
		assertEquals(Status.PENDING, updatedPayment.getStatus());
		verify(paymentRepository).save(paymentToUpdate);
	}

	@Test
	void updatePayment_throwsPaymentNotFoundException() {
		Payment paymentToUpdate = new Payment(1, 200.0, "updated-token", Status.PENDING);

		when(paymentRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(PaymentNotFoundException.class, () -> paymentService.updatePayment(1, paymentToUpdate));
		verify(paymentRepository, never()).save(paymentToUpdate);
	}

	@Test
	void deletePayment_withValidId_shouldDeletePayment() throws PaymentNotFoundException {
		Integer id = 1;

		paymentService.deletePayment(id);

		verify(paymentRepository).deleteById(id);
	}

	@Test
	void deletePayment_withInvalidId_shouldThrowPaymentNotFoundException() {
		Integer id = 1;

		doThrow(new EmptyResultDataAccessException(1)).when(paymentRepository).deleteById(id);

		assertThrows(PaymentNotFoundException.class, () -> paymentService.deletePayment(id));
	}
}
