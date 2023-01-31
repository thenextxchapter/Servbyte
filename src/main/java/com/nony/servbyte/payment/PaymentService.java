package com.nony.servbyte.payment;

import java.util.NoSuchElementException;

import com.nony.servbyte.exception.OrderNotFoundException;
import com.nony.servbyte.exception.PaymentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	private final PaymentRepository paymentRepo;

	@Autowired
	public PaymentService(PaymentRepository paymentRepo) {
		this.paymentRepo = paymentRepo;
	}

	public Payment createPayment(Payment payment) {
		return paymentRepo.save(payment);
	}

	public Payment getPaymentById(Integer id) throws PaymentNotFoundException {
		try {
			return paymentRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new PaymentNotFoundException();
		}
	}

	public Payment getPaymentByOrder(Integer orderId) throws PaymentNotFoundException {
		try {
			return paymentRepo.findByOrderId(orderId);
		} catch (NoSuchElementException exception) {
			throw new PaymentNotFoundException();
		}
	}

	public Payment updatePayment(Integer id, Payment payment) throws PaymentNotFoundException {
		Payment paymentToUpdate = getPaymentById(id);

		paymentToUpdate.setAmount(payment.getAmount());
		paymentToUpdate.setPaymentToken(payment.getPaymentToken());
		paymentToUpdate.setStatus(payment.getStatus());

		return paymentRepo.save(paymentToUpdate);
	}

	public void deletePayment(Integer id) throws PaymentNotFoundException {
		try {
			paymentRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new PaymentNotFoundException();
		}
	}
}
