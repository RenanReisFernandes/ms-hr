package com.renan.hrworker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renan.hrworker.entities.Worker;
import com.renan.hrworker.services.WorkerService;

@RestController
@RequestMapping("/worker")
public class WorkerController {
	
	@Autowired
	private WorkerService service;
	
	public ResponseEntity<Worker> save(@RequestBody Worker worker){
		Worker workerSaved = service.save(worker);
		return ResponseEntity.status(HttpStatus.CREATED).body(workerSaved);
	}

}
