package com.renan.hrpayroll.paymentService;

import com.renan.hrpayroll.dto.response.WorkerResponse;
import com.renan.hrpayroll.entities.Payment;
import com.renan.hrpayroll.feignclients.WorkerFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private WorkerFeignClient workerFeignClient;

    private WorkerResponse workerResponse;

    @BeforeEach
    void setUp() {
        workerResponse = new WorkerResponse();
        workerResponse.setName("John Doe");
        workerResponse.setDailyIncome(200.0);
    }

    @Test
    void testGetPayment() {
        when(workerFeignClient.findById(anyLong())).thenReturn(ResponseEntity.ok(workerResponse));

        Long workerId = 1L;
        Integer dias = 10;
        Payment payment = paymentService.getPayment(workerId, dias);

        assertEquals("John Doe", payment.getNome());
        assertEquals(200.0, payment.getRendimentoDiario());
        assertEquals(dias, payment.getDias());

        verify(workerFeignClient, times(1)).findById(workerId);
    }


}