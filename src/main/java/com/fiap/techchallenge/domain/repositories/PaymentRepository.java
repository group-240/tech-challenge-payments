package com.fiap.techchallenge.domain.repositories;

import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import org.bson.Document;

public interface PaymentRepository {
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
