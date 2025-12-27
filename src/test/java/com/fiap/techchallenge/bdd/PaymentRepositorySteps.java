package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.domain.repositories.PaymentRepository;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

public class PaymentRepositorySteps {
    private PaymentRepository repository;
    private PaymentStatusResponse response;

    @Dado("que existe um pagamento no repositório com id {string}")
    public void que_existe_um_pagamento_no_repositorio_com_id(String id) {
        repository = Mockito.mock(PaymentRepository.class);
        PaymentStatusResponse resp = new PaymentStatusResponse();
        resp.setId(id);
        Mockito.when(repository.getPaymentById(id)).thenReturn(resp);
    }

    @Dado("que não existe um pagamento no repositório com id {string}")
    public void que_nao_existe_um_pagamento_no_repositorio_com_id(String id) {
        repository = Mockito.mock(PaymentRepository.class);
        Mockito.when(repository.getPaymentById(id)).thenReturn(null);
    }

    @Quando("eu busco o pagamento no repositório pelo id {string}")
    public void eu_busco_o_pagamento_no_repositorio_pelo_id(String id) {
        response = repository.getPaymentById(id);
    }

    @Então("o pagamento do repositório deve ser encontrado")
    public void o_pagamento_do_repositorio_deve_ser_encontrado() {
        Assertions.assertNotNull(response);
    }

    @Então("o pagamento do repositório não deve ser encontrado")
    public void o_pagamento_do_repositorio_nao_deve_ser_encontrado() {
        Assertions.assertNull(response);
    }
}

