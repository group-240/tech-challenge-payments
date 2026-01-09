package com.fiap.techchallenge.external.api.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentOrderRequestTest {

    @Test
    void testGettersAndSetters() {
        PaymentOrderRequest req = new PaymentOrderRequest();
        req.setAmount(100.0);
        req.setDescription("desc");
        req.setPaymentMethodId("pix");
        req.setInstallments(2);
        req.setPayerEmail("email@teste.com");
        req.setIdentificationType("CPF");
        req.setIdentificationNumber("12345678900");

        assertEquals(100.0, req.getAmount());
        assertEquals("desc", req.getDescription());
        assertEquals("pix", req.getPaymentMethodId());
        assertEquals(2, req.getInstallments());
        assertEquals("email@teste.com", req.getPayerEmail());
        assertEquals("CPF", req.getIdentificationType());
        assertEquals("12345678900", req.getIdentificationNumber());
    }
}
