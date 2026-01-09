package com.fiap.techchallenge.external.api.controllers;
import com.fiap.techchallenge.external.api.dto.PaymentOrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarOrdemPagamentoComSucesso() throws Exception {
        PaymentOrderRequest req = new PaymentOrderRequest();
        req.setAmount(100.0);
        req.setDescription("Pedido teste");
        req.setPaymentMethodId("PIX");
        req.setInstallments(1);
        req.setPayerEmail("cliente@email.com");
        req.setIdentificationType("CPF");
        req.setIdentificationNumber("12345678900");

        mockMvc.perform(
                        post("/payment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(req))
                )
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarPagamentoPorIdComSucesso() throws Exception {
        mockMvc.perform(
                        get("/payment/123")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
}