package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.adapters.controllers.PaymentController;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class PaymentSteps {
    private PaymentController controller;
    private PaymentStatusResponse response;

    @Dado("que existe um pagamento com id {string}")
    public void que_existe_um_pagamento_com_id(String id) {
        controller = Mockito.mock(PaymentController.class);
        PaymentStatusResponse resp = new PaymentStatusResponse();
        resp.setId(id);
        resp.setStatus("approved");
        Mockito.when(controller.getPaymentById(id))
                .thenReturn(resp);
    }

    @Dado("que não existe um pagamento com id {string}")
    public void que_nao_existe_um_pagamento_com_id(String id) {
        controller = Mockito.mock(PaymentController.class);
        Mockito.when(controller.getPaymentById(id))
                .thenReturn(null);
    }

    @Quando("eu consulto o pagamento pelo id {string}")
    public void eu_consulto_o_pagamento_pelo_id(String id) {
        response = controller.getPaymentById(id);
    }

    @Então("o status da resposta deve ser {string}")
    public void o_status_da_resposta_deve_ser(String status) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(status, response.getStatus());
    }

    @Então("a resposta deve ser 404")
    public void a_resposta_deve_ser_404() {
        Assertions.assertNull(response);
    }
}