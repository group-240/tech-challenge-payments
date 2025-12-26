package com.fiap.techchallenge.external.datasource.mercadopago;

import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;

public interface MercadoPagoClient {
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
