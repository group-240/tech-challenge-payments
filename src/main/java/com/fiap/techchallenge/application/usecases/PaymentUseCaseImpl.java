package com.fiap.techchallenge.application.usecases;

import com.fiap.techchallenge.domain.repositories.PaymentRepository;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;

public class PaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentRepository paymentRepository;

    public PaymentUseCaseImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
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
        return paymentRepository.createPaymentOrder(
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
    public PaymentStatusResponse getPaymentById(String paymentId){
        return paymentRepository.getPaymentById(paymentId);
    }
}
