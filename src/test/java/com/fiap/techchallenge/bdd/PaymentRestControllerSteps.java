package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.external.api.PaymentRestController;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

public class PaymentRestControllerSteps {
    private PaymentRestController controller;
    private ResponseEntity<PaymentStatusResponse> responseEntity;

    @Dado("que existe um pagamento via REST com id {string}")
    public void que_existe_um_pagamento_via_rest_com_id(String id) {
        controller = Mockito.mock(PaymentRestController.class);
        PaymentStatusResponse resp = new PaymentStatusResponse();
        resp.setId(id);
        resp.setStatus("approved");
        Mockito.when(controller.getPaymentById(id))
                .thenReturn(ResponseEntity.ok(resp));
    }

    @Dado("que não existe um pagamento via REST com id {string}")
    public void que_nao_existe_um_pagamento_via_rest_com_id(String id) {
        controller = Mockito.mock(PaymentRestController.class);
        Mockito.when(controller.getPaymentById(id))
                .thenReturn(ResponseEntity.notFound().build());
    }

    @Quando("eu consulto o pagamento via REST pelo id {string}")
    public void eu_consulto_o_pagamento_via_rest_pelo_id(String id) {
        responseEntity = controller.getPaymentById(id);
    }

    @Então("o status da resposta REST deve ser {string}")
    public void o_status_da_resposta_rest_deve_ser(String status) {
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(status, responseEntity.getBody().getStatus());
    }

    @Então("a resposta REST deve ser 404")
    public void a_resposta_rest_deve_ser_404() {
        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
    }
}

