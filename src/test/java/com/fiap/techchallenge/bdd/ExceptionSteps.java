package com.fiap.techchallenge.bdd;

import com.fiap.techchallenge.domain.exception.PaymentNotFoundException;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;

public class ExceptionSteps {
    private Exception exception;

    @Quando("eu lanço PaymentNotFoundException com mensagem {string}")
    public void eu_lanco_paymentnotfoundexception_com_mensagem(String msg) {
        try {
            throw new PaymentNotFoundException(msg);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Então("a mensagem da exceção deve ser {string}")
    public void a_mensagem_da_excecao_deve_ser(String msg) {
        Assertions.assertEquals(msg, exception.getMessage());
    }
}

