package com.fiap.techchallenge.bdd.steps;

import com.fiap.techchallenge.adapters.controllers.PaymentController;
import com.fiap.techchallenge.application.usecases.PaymentUseCase;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class PaymentControllerSteps {

    @Autowired
    private PaymentController paymentController;

    @Autowired
    private PaymentUseCase paymentUseCase;

    private PaymentResponse paymentResponse;
    private PaymentStatusResponse statusResponse;

    @Given("que o use case de pagamento retorna sucesso")
    public void mock_create_payment() {
        when(paymentUseCase.createPaymentOrder(
                anyDouble(),
                anyString(),
                anyString(),
                anyInt(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(new PaymentResponse());
    }

    @When("eu crio uma ordem de pagamento")
    public void create_payment() {
        paymentResponse = paymentController.createPaymentOrder(
                100.0,
                "Pedido teste",
                "PIX",
                1,
                "cliente@email.com",
                "CPF",
                "12345678900"
        );
    }

    @Then("a ordem de pagamento deve ser criada com sucesso")
    public void validate_create_payment() {
        assertNotNull(paymentResponse);
        verify(paymentUseCase, times(1)).createPaymentOrder(
                anyDouble(),
                anyString(),
                anyString(),
                anyInt(),
                anyString(),
                anyString(),
                anyString()
        );
    }

    @Given("que existe um pagamento com id {string}")
    public void mock_get_payment(String paymentId) {
        when(paymentUseCase.getPaymentById(paymentId))
                .thenReturn(new PaymentStatusResponse());
    }

    @When("eu busco o pagamento pelo id")
    public void get_payment() {
        statusResponse = paymentController.getPaymentById("123");
    }

    @Then("o status do pagamento deve ser retornado")
    public void validate_get_payment() {
        assertNotNull(statusResponse);
        verify(paymentUseCase, times(1)).getPaymentById("123");
    }
}
