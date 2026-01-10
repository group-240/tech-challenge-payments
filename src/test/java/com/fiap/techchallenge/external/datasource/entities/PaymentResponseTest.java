package com.fiap.techchallenge.external.datasource.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentResponseTest {

    @Test
    void testGettersAndSetters() {
        PaymentResponse resp = new PaymentResponse();
        resp.setId(10L);
        resp.setStatus("pending");

        assertEquals(10L, resp.getId());
        assertEquals("pending", resp.getStatus());
    }}