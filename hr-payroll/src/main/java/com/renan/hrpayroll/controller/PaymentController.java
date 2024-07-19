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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
	@Autowired
	private PaymentService service;
	
	@Operation(summary = "Realiza chamada do valor do pagamento pelo funcionário/dias/valor da diária", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca valores de pagamentos"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "401", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro de servidor")
			
	})
	
	@GetMapping(value = "/{workerId}/dias/{dias}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer dias){
		Payment payment = service.getPayment(workerId, dias);
		return ResponseEntity.status(HttpStatus.FOUND).body(payment);
	}

}
