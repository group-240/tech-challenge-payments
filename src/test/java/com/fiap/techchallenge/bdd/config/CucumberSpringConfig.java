package com.fiap.techchallenge.bdd.config;

import com.fiap.techchallenge.adapters.controllers.PaymentController;
import com.fiap.techchallenge.application.usecases.PaymentUseCase;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class CucumberSpringConfig {

    @MockBean
    private PaymentUseCase paymentUseCase;
}
