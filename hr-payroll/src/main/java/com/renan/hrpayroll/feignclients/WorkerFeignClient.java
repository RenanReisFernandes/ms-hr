package com.renan.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.renan.hrpayroll.dto.response.WorkerResponse;


@Component
@FeignClient(name = "hr-worker", url = "localhost:8001", path = "/worker")
public interface WorkerFeignClient {
	
	@GetMapping("/{id}")
	ResponseEntity<WorkerResponse> findById(@PathVariable Long id);
	


}
