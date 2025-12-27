package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.adapters.gateway.PaymentRepositoryGateway;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

public class PaymentRepositoryGatewaySteps {
    private PaymentRepositoryGateway gateway;
    private PaymentStatusResponse response;

    @Dado("que existe um pagamento no gateway com id {string}")
    public void que_existe_um_pagamento_no_gateway_com_id(String id) {
        gateway = Mockito.mock(PaymentRepositoryGateway.class);
        PaymentStatusResponse resp = new PaymentStatusResponse();
        resp.setId(id);
        Mockito.when(gateway.getPaymentById(id)).thenReturn(resp);
    }

    @Dado("que não existe um pagamento no gateway com id {string}")
    public void que_nao_existe_um_pagamento_no_gateway_com_id(String id) {
        gateway = Mockito.mock(PaymentRepositoryGateway.class);
        Mockito.when(gateway.getPaymentById(id)).thenReturn(null);
    }

    @Quando("eu busco o pagamento no gateway pelo id {string}")
    public void eu_busco_o_pagamento_no_gateway_pelo_id(String id) {
        response = gateway.getPaymentById(id);
    }

    @Então("o pagamento do gateway deve ser encontrado")
    public void o_pagamento_do_gateway_deve_ser_encontrado() {
        Assertions.assertNotNull(response);
    }

    @Então("o pagamento do gateway não deve ser encontrado")
    public void o_pagamento_do_gateway_nao_deve_ser_encontrado() {
        Assertions.assertNull(response);
    }
}

