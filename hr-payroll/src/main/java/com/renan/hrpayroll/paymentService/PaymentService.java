package com.renan.hrpayroll.paymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renan.hrpayroll.dto.response.WorkerResponse;
import com.renan.hrpayroll.entities.Payment;
import com.renan.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
    
    @Autowired
    private WorkerFeignClient workerFeignClient;
    
    public Payment getPayment(Long workerId, Integer dias) {
        WorkerResponse worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), dias);
    }
}