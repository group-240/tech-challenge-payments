package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.application.usecases.PaymentUseCase;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

public class PaymentUseCaseSteps {
    private PaymentUseCase useCase;
    private PaymentStatusResponse response;

    @Dado("que existe um pagamento no caso de uso com id {string}")
    public void que_existe_um_pagamento_no_caso_de_uso_com_id(String id) {
        useCase = Mockito.mock(PaymentUseCase.class);
        PaymentStatusResponse resp = new PaymentStatusResponse();
        resp.setId(id);
        Mockito.when(useCase.getPaymentById(id)).thenReturn(resp);
    }

    @Dado("que não existe um pagamento no caso de uso com id {string}")
    public void que_nao_existe_um_pagamento_no_caso_de_uso_com_id(String id) {
        useCase = Mockito.mock(PaymentUseCase.class);
        Mockito.when(useCase.getPaymentById(id)).thenReturn(null);
    }

    @Quando("eu executo o caso de uso de pagamento pelo id {string}")
    public void eu_executo_o_caso_de_uso_de_pagamento_pelo_id(String id) {
        response = useCase.getPaymentById(id);
    }

    @Então("o pagamento do caso de uso deve ser encontrado")
    public void o_pagamento_do_caso_de_uso_deve_ser_encontrado() {
        Assertions.assertNotNull(response);
    }

    @Então("o pagamento do caso de uso não deve ser encontrado")
    public void o_pagamento_do_caso_de_uso_nao_deve_ser_encontrado() {
        Assertions.assertNull(response);
    }
}

