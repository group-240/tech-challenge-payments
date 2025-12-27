package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.external.api.dto.PaymentOrderRequest;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;

public class PaymentOrderRequestDTOSteps {
    private PaymentOrderRequest request;

    @Dado("um PaymentOrderRequest")
    public void um_paymentorderrequest() {
        request = new PaymentOrderRequest();
    }

    @Quando("eu seto os campos do PaymentOrderRequest")
    public void eu_seto_os_campos_do_paymentorderrequest() {
        request.setAmount(200.0);
        request.setDescription("desc");
        request.setPaymentMethodId("visa");
        request.setInstallments(2);
        request.setPayerEmail("email@test.com");
        request.setIdentificationType("CPF");
        request.setIdentificationNumber("12345678900");
    }

    @Então("os getters do PaymentOrderRequest retornam os valores corretos")
    public void os_getters_do_paymentorderrequest_retornam_os_valores_corretos() {
        Assertions.assertEquals(200.0, request.getAmount());
        Assertions.assertEquals("desc", request.getDescription());
        Assertions.assertEquals("visa", request.getPaymentMethodId());
        Assertions.assertEquals(2, request.getInstallments());
        Assertions.assertEquals("email@test.com", request.getPayerEmail());
        Assertions.assertEquals("CPF", request.getIdentificationType());
        Assertions.assertEquals("12345678900", request.getIdentificationNumber());
    }
}
