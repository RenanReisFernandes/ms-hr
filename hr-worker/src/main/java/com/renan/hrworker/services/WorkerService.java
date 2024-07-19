package com.renan.hrworker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renan.hrworker.entities.Worker;
import com.renan.hrworker.repositories.WorkerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
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
	
	public List<Worker> findAll(){
		return repository.findAll();
	}
	
	public Optional<Worker> findById(Long id) {
		return repository.findById(id);
	}
	
	public Worker update(Worker worker, Long id) {
		Optional<Worker> optWorker = repository.findById(id);
		if(optWorker.isPresent()) {
			Worker workerExistent = optWorker.get();
			
			workerExistent.setName(worker.getName());
			workerExistent.setCpf(worker.getCpf());
			workerExistent.setAddress(worker.getAddress());
			workerExistent.setAgency(worker.getAgency());
			workerExistent.setAccount(worker.getAccount());
			workerExistent.setDailyIncome(worker.getDailyIncome());
			
			return repository.save(workerExistent);
		}
		else {
			throw new RuntimeException("Funcionário não encontrado");
		}
		
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
