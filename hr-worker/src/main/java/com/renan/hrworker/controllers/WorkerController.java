package com.renan.hrworker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public ResponseEntity<Worker> save(@RequestBody Worker worker){
		Worker workerSaved = service.save(worker);
		return ResponseEntity.status(HttpStatus.CREATED).body(workerSaved);
	}
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> list = service.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Worker>> findById(@PathVariable Long id){
		Optional<Worker> worker = service.findById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(worker);
	}

}
