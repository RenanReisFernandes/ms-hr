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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value ="/worker")
@Tag(name = "API WORKER")
public class WorkerController {
	
	@Autowired
	private WorkerService service;
	
	@Autowired
	private  WorkerMapper mapper;
	
	@Operation(summary = "Realiza inserção de funcionários", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Inserção realizada com sucesso"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "401", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro de servidor")
			
	})
	@PostMapping
	public ResponseEntity<WorkerResponse> save(@RequestBody WorkerRequest workerRequest){
		Worker worker = mapper.toWorker(workerRequest);
		Worker workerSaved = service.save(worker);
		WorkerResponse workerResponse = mapper.toWorkerResponse(workerSaved);
		return ResponseEntity.status(HttpStatus.CREATED).body(workerResponse);
	}
	
	@Operation(summary = "Realiza busca de todos os funcionários cadastrados", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "401", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro de servidor")
			
	})
	
	@GetMapping
	public ResponseEntity<List<WorkerResponse>> findAll(){
		List<Worker> list = service.findAll();
		List<WorkerResponse> listResponse = mapper.toWorkerResponseList(list);
		return ResponseEntity.status(HttpStatus.FOUND).body(listResponse);
	}
	
	@Operation(summary = "Realiza busca de um funcionário através do ID", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "401", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro de servidor")
			
	})
	
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
	
	@Operation(summary = "Realiza atualização dos dados de um funcionário através do ID", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "401", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro de servidor")
			
	})
	
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
	
	@Operation(summary = "Realiza deleção de um funcionário através do ID", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleção realizada com sucesso"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "401", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro de servidor"),
			
	})
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		service.delete(id);
	}

}
