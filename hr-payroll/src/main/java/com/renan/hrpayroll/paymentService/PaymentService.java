package com.renan.hrpayroll.paymentService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.renan.hrpayroll.entities.Payment;
import com.renan.hrpayroll.entities.Worker;
import com.renan.hrpayroll.feignclients.WorkerFeignClient;
import com.renan.hrpayroll.feignclients.DTO.response.WorkerResponse;

@Service
public class PaymentService {
	
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	public Payment getPayment(long workerId, int dias) {
		
		WorkerResponse worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(),worker.getDailyIncome(),dias);
	}

}
