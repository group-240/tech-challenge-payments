package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;

public class PaymentStatusResponseSteps {
    private PaymentStatusResponse response;

    @Dado("um PaymentStatusResponse")
    public void um_paymentstatusresponse() {
        response = new PaymentStatusResponse();
    }

    @Quando("eu seto os campos")
    public void eu_seto_os_campos() {
        response.setId("1");
        response.setStatus("approved");
        response.setStatus_detail("detail");
        response.setTransaction_amount(100.0);
        response.setDescription("desc");
        response.setPayment_method_id("visa");
        response.setPayer_email("email@test.com");
    }

    @Então("os getters retornam os valores corretos")
    public void os_getters_retornam_os_valores_corretos() {
        Assertions.assertEquals("1", response.getId());
        Assertions.assertEquals("approved", response.getStatus());
        Assertions.assertEquals("detail", response.getStatus_detail());
        Assertions.assertEquals(100.0, response.getTransaction_amount());
        Assertions.assertEquals("desc", response.getDescription());
        Assertions.assertEquals("visa", response.getPayment_method_id());
        Assertions.assertEquals("email@test.com", response.getPayer_email());
    }
}

