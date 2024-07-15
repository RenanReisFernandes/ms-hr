package com.renan.hrpayroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renan.hrpayroll.entities.Payment;
import com.renan.hrpayroll.paymentService.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
	@Autowired
	private PaymentService service;
	
	@GetMapping(value = "/{workerId}/dias/{dias}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer dias){
		Payment payment = service.getPayment(workerId, dias);
		return ResponseEntity.status(HttpStatus.FOUND).body(payment);
	}

}
