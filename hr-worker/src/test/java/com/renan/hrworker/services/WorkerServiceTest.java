package com.renan.hrworker.services;

import com.renan.hrworker.entities.Worker;
import com.renan.hrworker.repositories.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {

    @Mock
    private WorkerRepository repository;

    @InjectMocks
    private WorkerService service;

    private Worker worker;

    @BeforeEach
    void setUp() {
        worker = new Worker();
        worker.setId(1L);
        worker.setName("John Doe");
        worker.setCpf("12345678900");
        worker.setAddress("123 Street");
        worker.setAgency("Agency");
        worker.setAccount("Account");
        worker.setDailyIncome(100.0);
    }

    @Test
    void testSaveNewWorker() {
        when(repository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(repository.save(any(Worker.class))).thenReturn(worker);

        Worker savedWorker = service.save(worker);

        assertNotNull(savedWorker);
        assertEquals(worker, savedWorker);
        verify(repository, times(1)).findByCpf(worker.getCpf());
        verify(repository, times(1)).save(worker);
    }

    @Test
    void testSaveExistingWorkerThrowsException() {
        Worker existingWorker = new Worker();
        existingWorker.setId(2L);
        existingWorker.setCpf(worker.getCpf());

        when(repository.findByCpf(anyString())).thenReturn(Optional.of(existingWorker));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.save(worker));
        assertEquals("Funcionário já cadastrado!", exception.getMessage());
        verify(repository, times(1)).findByCpf(worker.getCpf());
        verify(repository, times(0)).save(worker);
    }

    @Test
    void testFindAll() {
        List<Worker> workers = List.of(worker);

        when(repository.findAll()).thenReturn(workers);

        List<Worker> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(worker, result.get(0));
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(worker));

        Optional<Worker> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(worker, result.get());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdateExistingWorker() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(worker));
        when(repository.save(any(Worker.class))).thenReturn(worker);

        Worker updatedWorker = new Worker();
        updatedWorker.setName("Jane Doe");
        updatedWorker.setCpf("09876543211");
        updatedWorker.setAddress("456 Avenue");
        updatedWorker.setAgency("New Agency");
        updatedWorker.setAccount("New Account");
        updatedWorker.setDailyIncome(150.0);

        Worker result = service.update(updatedWorker, 1L);

        assertNotNull(result);
        assertEquals(updatedWorker.getName(), result.getName());
        assertEquals(updatedWorker.getCpf(), result.getCpf());
        assertEquals(updatedWorker.getAddress(), result.getAddress());
        assertEquals(updatedWorker.getAgency(), result.getAgency());
        assertEquals(updatedWorker.getAccount(), result.getAccount());
        assertEquals(updatedWorker.getDailyIncome(), result.getDailyIncome());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(worker);
    }

    @Test
    void testUpdateNonExistingWorkerThrowsException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Worker updatedWorker = new Worker();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.update(updatedWorker, 1L));
        assertEquals("Funcionário não encontrado", exception.getMessage());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(0)).save(any(Worker.class));
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(anyLong());

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}