package com.fiap.techchallenge.application.config;

import com.fiap.techchallenge.adapters.controllers.PaymentController;
import com.fiap.techchallenge.adapters.gateway.PaymentRepositoryGateway;
import com.fiap.techchallenge.application.usecases.PaymentUseCase;
import com.fiap.techchallenge.application.usecases.PaymentUseCaseImpl;
import com.fiap.techchallenge.domain.repositories.PaymentRepository;
import com.fiap.techchallenge.external.datasource.mercadopago.MercadoPagoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PaymentRepository paymentRepository(MercadoPagoClient mercadoPagoClient) {
        return new PaymentRepositoryGateway(mercadoPagoClient);
    }


    @Bean
    public PaymentUseCase paymentUseCase(PaymentRepository paymentRepository) {
        return new PaymentUseCaseImpl(paymentRepository);
    }


    @Bean
    public PaymentController paymentController(PaymentUseCase paymentUseCase) {
        return new PaymentController(paymentUseCase);
    }

}
