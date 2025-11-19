package com.fiap.techchallenge.application.config;

import com.fiap.techchallenge.adapters.controllers.*;
import com.fiap.techchallenge.adapters.gateway.*;
import com.fiap.techchallenge.application.usecases.*;
import com.fiap.techchallenge.domain.repositories.*;
import com.fiap.techchallenge.external.datasource.repositories.*;
import com.fiap.techchallenge.external.datasource.mercadopago.MercadoPagoClient;
import com.fiap.techchallenge.external.cognito.CognitoService;
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
