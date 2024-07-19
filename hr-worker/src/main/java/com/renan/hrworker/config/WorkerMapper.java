package com.renan.hrworker.config;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.renan.hrworker.DTO.request.WorkerRequest;
import com.renan.hrworker.DTO.response.WorkerResponse;
import com.renan.hrworker.entities.Worker;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkerMapper {
	
	private final ModelMapper mapper;
	
	public Worker toWorker(WorkerRequest workerRequest) {
		return mapper.map(workerRequest, Worker.class);
	}
	
	public WorkerResponse toWorkerResponse(Worker worker) {
		return mapper.map(worker, WorkerResponse.class);
	}
	
	public List<WorkerResponse> toWorkerResponseList(List<Worker> workers){
		return workers.stream()
				.map(this:: toWorkerResponse)
				.collect(Collectors.toList());
	}

}
