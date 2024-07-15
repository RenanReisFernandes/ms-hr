package com.renan.hrpayroll.paymentService;

import org.springframework.stereotype.Service;

import com.renan.hrpayroll.entities.Payment;

@Service
public class PaymentService {
	
	public Payment getPayment(long workerId, int dias) {
		return new Payment("Renan", 20000.00,dias);
	}

}
