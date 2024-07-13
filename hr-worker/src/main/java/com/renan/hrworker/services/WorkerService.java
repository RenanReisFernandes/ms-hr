package com.renan.hrworker.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renan.hrworker.entities.Worker;
import com.renan.hrworker.repositories.WorkerRepository;

@Service
public class WorkerService {
	@Autowired
	private WorkerRepository repository;
	
	public Worker save(Worker worker) {
		Boolean existCpf = false;
		
		Optional<Worker> optWorker =repository.findByCpf(worker.getCpf());
		if(optWorker.isPresent()) {
			if(!optWorker.get().getId().equals(worker.getId())) {
				
				existCpf = true;
			}
			if(existCpf) {
				throw new RuntimeException("Funcionário já cadastrado!");
			}
			
		}
		return repository.save(worker);
	}

}
