package com.renan.hrworker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renan.hrworker.DTO.request.WorkerRequest;
import com.renan.hrworker.DTO.response.WorkerResponse;
import com.renan.hrworker.config.WorkerMapper;
import com.renan.hrworker.entities.Worker;
import com.renan.hrworker.services.WorkerService;

@RestController
@RequestMapping("/worker")
public class WorkerController {
	
	@Autowired
	private WorkerService service;
	
	@Autowired
	private  WorkerMapper mapper;
	
	@PostMapping
	public ResponseEntity<WorkerResponse> save(@RequestBody WorkerRequest workerRequest){
		Worker worker = mapper.toWorker(workerRequest);
		Worker workerSaved = service.save(worker);
		WorkerResponse workerResponse = mapper.toWorkerResponse(workerSaved);
		return ResponseEntity.status(HttpStatus.CREATED).body(workerResponse);
	}
	
	@GetMapping
	public ResponseEntity<List<WorkerResponse>> findAll(){
		List<Worker> list = service.findAll();
		List<WorkerResponse> listResponse = mapper.toWorkerResponseList(list);
		return ResponseEntity.status(HttpStatus.FOUND).body(listResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WorkerResponse> findById(@PathVariable Long id){
		Optional<Worker> worker = service.findById(id);
		if(worker.isPresent()) {
			WorkerResponse workerResponse = mapper.toWorkerResponse(worker.get());
			return ResponseEntity.status(HttpStatus.FOUND).body(workerResponse);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<WorkerResponse> update(@RequestBody WorkerRequest workerUpdated, @PathVariable Long id){
		try {
			Worker toWorker = mapper.toWorker(workerUpdated);
		Worker worker = service.update(toWorker, id);
		WorkerResponse toResponse = mapper.toWorkerResponse(worker);
		return ResponseEntity.status(HttpStatus.OK).body(toResponse);
		}
		catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		service.delete(id);
	}

}
