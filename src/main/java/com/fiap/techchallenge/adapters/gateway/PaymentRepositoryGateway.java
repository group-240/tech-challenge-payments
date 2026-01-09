package com.fiap.techchallenge.adapters.gateway;

import com.fiap.techchallenge.domain.repositories.PaymentRepository;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import com.fiap.techchallenge.external.datasource.mercadopago.MercadoPagoClient;

public class PaymentRepositoryGateway implements PaymentRepository {

    private final MercadoPagoClient mercadoPagoClient;

    public PaymentRepositoryGateway(MercadoPagoClient mercadoPagoClient) {
        this.mercadoPagoClient = mercadoPagoClient;
    }

    @Override
    public PaymentResponse createPaymentOrder(
        Double amount,
        String description,
        String paymentMethodId,
        Integer installments,
        String payerEmail,
        String identificationType,
        String identificationNumber
    ) {
        return mercadoPagoClient.createPaymentOrder(
            amount,
            description,
            paymentMethodId,
            installments,
            payerEmail,
            identificationType,
            identificationNumber
        );
    }

    @Override
    public PaymentStatusResponse getPaymentById(String paymentId) {
        return mercadoPagoClient.getPaymentById(paymentId);
    }
}
