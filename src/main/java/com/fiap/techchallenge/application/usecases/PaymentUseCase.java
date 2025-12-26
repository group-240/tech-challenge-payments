package com.fiap.techchallenge.application.usecases;

import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;

public interface PaymentUseCase {
    PaymentResponse createPaymentOrder(
        Double amount,
        String description,
        String paymentMethodId,
        Integer installments,
        String payerEmail,
        String identificationType,
        String identificationNumber
    );
}
