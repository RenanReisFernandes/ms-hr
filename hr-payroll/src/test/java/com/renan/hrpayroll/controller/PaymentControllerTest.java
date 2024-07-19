package com.renan.hrpayroll.controller;

import com.renan.hrpayroll.entities.Payment;
import com.renan.hrpayroll.paymentService.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment("John Doe", 200.0, 10);
    }

    @Test
    void testGetPayment() {
        when(paymentService.getPayment(anyLong(), anyInt())).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.getPayment(1L, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());

        verify(paymentService, times(1)).getPayment(1L, 10);
    }

    @Test
    void testGetPaymentWhenServiceReturnsNull() {
        when(paymentService.getPayment(anyLong(), anyInt())).thenReturn(null);

        ResponseEntity<Payment> response = paymentController.getPayment(1L, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());

        verify(paymentService, times(1)).getPayment(1L, 10);
    }
}