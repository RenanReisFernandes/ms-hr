package com.renan.hrworker.controllers;

import com.renan.hrworker.DTO.request.WorkerRequest;
import com.renan.hrworker.DTO.response.WorkerResponse;
import com.renan.hrworker.config.WorkerMapper;
import com.renan.hrworker.entities.Worker;
import com.renan.hrworker.services.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WorkerController.class)
@ExtendWith(MockitoExtension.class)
class WorkerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkerService service;

    @MockBean
    private WorkerMapper mapper;

    private Worker worker;
    private WorkerRequest workerRequest;
    private WorkerResponse workerResponse;

    @BeforeEach
    void setUp() {
        worker = new Worker();
        worker.setId(1L);
        worker.setName("John Doe");
        worker.setCpf("12345678900");
        worker.setAddress("123 Street");
        worker.setAgency(333L);
        worker.setAccount(23L);
        worker.setDailyIncome(100.0);

        workerRequest = new WorkerRequest();
        workerRequest.setName("John Doe");
        workerRequest.setCpf("12345678900");
        workerRequest.setAddress("123 Street");
        workerRequest.setDailyIncome(100.0);

        workerResponse = new WorkerResponse();
        workerResponse.setName("John Doe");
        workerResponse.setCpf("12345678900");
        workerResponse.setAddress("123 Street");
        workerResponse.setDailyIncome(100.0);
    }

    @Test
    void testSaveWorker() throws Exception {
        when(mapper.toWorker(any(WorkerRequest.class))).thenReturn(worker);
        when(service.save(any(Worker.class))).thenReturn(worker);
        when(mapper.toWorkerResponse(any(Worker.class))).thenReturn(workerResponse);

        mockMvc.perform(post("/worker")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"cpf\":\"12345678900\",\"address\":\"123 Street\",\"agency\":\"Agency\",\"account\":\"Account\",\"dailyIncome\":100.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));

        verify(service, times(1)).save(any(Worker.class));
    }

    @Test
    void testFindAllWorkers() throws Exception {
        when(service.findAll()).thenReturn(List.of(worker));
        when(mapper.toWorkerResponseList(anyList())).thenReturn(List.of(workerResponse));

        mockMvc.perform(get("/worker")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].cpf").value("12345678900"));

        verify(service, times(1)).findAll();
    }

    @Test
    void testFindWorkerById() throws Exception {
        when(service.findById(anyLong())).thenReturn(Optional.of(worker));
        when(mapper.toWorkerResponse(any(Worker.class))).thenReturn(workerResponse);

        mockMvc.perform(get("/worker/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));

        verify(service, times(1)).findById(anyLong());
    }

    @Test
    void testFindWorkerByIdNotFound() throws Exception {
        when(service.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/worker/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateWorker() throws Exception {
        when(mapper.toWorker(any(WorkerRequest.class))).thenReturn(worker);
        when(service.update(any(Worker.class), anyLong())).thenReturn(worker);
        when(mapper.toWorkerResponse(any(Worker.class))).thenReturn(workerResponse);

        mockMvc.perform(put("/worker/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"cpf\":\"12345678900\",\"address\":\"123 Street\",\"agency\":\"Agency\",\"account\":\"Account\",\"dailyIncome\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));

        verify(service, times(1)).update(any(Worker.class), anyLong());
    }

    @Test
    void testUpdateWorkerNotFound() throws Exception {
        when(mapper.toWorker(any(WorkerRequest.class))).thenReturn(worker);
        when(service.update(any(Worker.class), anyLong())).thenThrow(new RuntimeException("Funcionário não encontrado"));

        mockMvc.perform(put("/worker/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"cpf\":\"12345678900\",\"address\":\"123 Street\",\"agency\":\"Agency\",\"account\":\"Account\",\"dailyIncome\":100.0}"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).update(any(Worker.class), anyLong());
    }

    @Test
    void testDeleteWorker() throws Exception {
        doNothing().when(service).delete(anyLong());

        mockMvc.perform(delete("/worker/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).delete(anyLong());
    }
}