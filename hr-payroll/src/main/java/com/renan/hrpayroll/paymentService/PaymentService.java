package com.renan.hrpayroll.paymentService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.renan.hrpayroll.entities.Payment;
import com.renan.hrpayroll.entities.Worker;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Payment getPayment(long workerId, int dias) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id",""+ workerId);
		
		Worker worker = restTemplate.getForObject(workerHost + "/worker/{id}", Worker.class, uriVariables);
		return new Payment(worker.getName(),worker.getDailyIncome(),dias);
	}

}
