package com.fiap.techchallenge.adapters.controllers;

import com.fiap.techchallenge.application.usecases.PaymentUseCase;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;

public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    public PaymentResponse createPaymentOrder(
        Double amount,
        String description,
        String paymentMethodId,
        Integer installments,
        String payerEmail,
        String identificationType,
        String identificationNumber
    ) {
        return paymentUseCase.createPaymentOrder(
            amount,
            description,
            paymentMethodId,
            installments,
            payerEmail,
            identificationType,
            identificationNumber
        );
    }

    public PaymentStatusResponse getPaymentById(String paymentId){
        return paymentUseCase.getPaymentById(paymentId);
    }
}
