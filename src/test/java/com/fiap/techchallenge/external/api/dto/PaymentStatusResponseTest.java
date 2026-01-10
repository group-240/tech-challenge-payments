package com.fiap.techchallenge.external.api.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentStatusResponseTest {

    @Test
    void testGettersAndSetters() {
        PaymentStatusResponse resp = new PaymentStatusResponse();
        resp.setId("1");
        resp.setStatus("approved");
        resp.setStatus_detail("detail");

        assertEquals("1", resp.getId());
        assertEquals("approved", resp.getStatus());
        assertEquals("detail", resp.getStatus_detail());
    }

}