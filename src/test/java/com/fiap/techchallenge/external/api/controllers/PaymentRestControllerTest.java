package com.fiap.techchallenge.external.api.controllers;

import com.fiap.techchallenge.adapters.controllers.PaymentController;
import com.fiap.techchallenge.external.api.dto.PaymentOrderRequest;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.http.HttpStatus.*;

class PaymentRestControllerTest {

    @Test
    void deveRetornarBadRequestQuandoCamposObrigatoriosForemNulos() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deveRetornarOkQuandoCriarOrdemComSucesso() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setAmount(1.0);
        request.setDescription("desc");
        request.setPaymentMethodId("pix");
        request.setInstallments(1);
        request.setPayerEmail("email@teste.com");
        request.setIdentificationType("CPF");
        request.setIdentificationNumber("12345678900");

        PaymentResponse resp = new PaymentResponse();
        resp.setId(1L);
        Mockito.when(paymentController.createPaymentOrder(
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(resp);

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void deveRetornarBadRequestQuandoLancarException() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setAmount(1.0);
        request.setDescription("desc");
        request.setPaymentMethodId("pix");
        request.setInstallments(1);
        request.setPayerEmail("email@teste.com");
        request.setIdentificationType("CPF");
        request.setIdentificationNumber("12345678900");

        Mockito.when(paymentController.createPaymentOrder(
                any(), any(), any(), any(), any(), any(), any()
        )).thenThrow(new RuntimeException());

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deveRetornarOkAoBuscarPagamentoPorId() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);

        PaymentStatusResponse statusResponse = new PaymentStatusResponse();
        Mockito.when(paymentController.getPaymentById("123")).thenReturn(statusResponse);

        ResponseEntity<PaymentStatusResponse> response = controller.getPaymentById("123");

        assertEquals(OK, response.getStatusCode());
        assertEquals(statusResponse, response.getBody());
    }

    @Test
    void deveRetornarNotFoundAoBuscarPagamentoPorIdInexistente() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);

        Mockito.when(paymentController.getPaymentById("999")).thenReturn(null);

        ResponseEntity<PaymentStatusResponse> response = controller.getPaymentById("999");

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    void deveRetornarBadRequestQuandoAmountForNulo() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setPaymentMethodId("pix");
        request.setInstallments(1);

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deveRetornarBadRequestQuandoPaymentMethodIdForNulo() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setAmount(1.0);
        request.setInstallments(1);

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deveRetornarBadRequestQuandoInstallmentsForNulo() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setAmount(1.0);
        request.setPaymentMethodId("pix");

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deveRetornarOkQuandoCamposOpcionaisForemNulos() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setAmount(1.0);
        request.setPaymentMethodId("pix");
        request.setInstallments(1);
        // Campos opcionais nulos

        PaymentResponse resp = new PaymentResponse();
        resp.setId(2L);
        Mockito.when(paymentController.createPaymentOrder(
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(resp);

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2L, response.getBody().getId());
    }

    @Test
    void deveRetornarBadRequestQuandoTodosObrigatoriosForemNulos() {
        PaymentController paymentController = Mockito.mock(PaymentController.class);
        PaymentRestController controller = new PaymentRestController(paymentController);
        PaymentOrderRequest request = new PaymentOrderRequest();

        ResponseEntity<PaymentResponse> response = controller.createOrder(request);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }


}
