package com.fiap.techchallenge.external.datasource.mercadopago;

import com.fiap.techchallenge.domain.exception.DomainException;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import com.fiap.techchallenge.infrastructure.logging.LogCategory;
import com.fiap.techchallenge.infrastructure.logging.StructuredLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.UUID;

@Component
public class MercadoPagoClientImpl implements MercadoPagoClient {

    @Autowired
    private PaymentService paymentService;

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoClientImpl.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mercado-pago.access-token}")
    private String accessToken;

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
        long startTime = System.currentTimeMillis();

        try {
            StructuredLogger.setCategory(LogCategory.INTEGRATION);
            StructuredLogger.setOperation("CreateMercadoPagoPayment");
            StructuredLogger.put("amount", String.valueOf(amount));
            StructuredLogger.put("paymentMethod", paymentMethodId);
            
            logger.info("MercadoPago payment creation started: amount={}, paymentMethod={}", 
                       amount, paymentMethodId);
            
            String url = "https://api.mercadopago.com/v1/payments";
            String notificationUrl = "https://example.com/notify";

            // Configura os headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);
            String idempotencyKey = UUID.randomUUID().toString();
            headers.set("X-Idempotency-Key", idempotencyKey);
            
            StructuredLogger.put("idempotencyKey", idempotencyKey);

            // Monta o corpo da requisição (JSON)
            StringBuilder requestBody = new StringBuilder("{");
            requestBody.append(String.format(Locale.US, "\"transaction_amount\":%.2f,", amount));
            requestBody.append(String.format("\"description\":\"%s\",", description));
            requestBody.append(String.format("\"payment_method_id\":\"%s\",", paymentMethodId));
            requestBody.append(String.format("\"installments\":%d", installments));
            requestBody.append(",");
            requestBody.append("\"payer\":{");
            requestBody.append(String.format("\"email\":\"%s\"", "brunoaugustoloc@gmail.com"));
            if (identificationType != null && identificationNumber != null) {
                requestBody.append(",");
                requestBody.append(String.format("\"identification\":{\"type\":\"%s\",\"number\":\"%s\"}", identificationType, identificationNumber));
            }
            requestBody.append("}");
            requestBody.append(String.format(",\"notification_url\":\"%s\"", notificationUrl));
            requestBody.append("}");

            HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

            String response = restTemplate.exchange(url, HttpMethod.POST, request, String.class).getBody();

            paymentService.savePaymentJson(response);

            PaymentResponse payment = paymentService.parsePayment(response);

            long duration = System.currentTimeMillis() - startTime;
            StructuredLogger.setDuration(duration);

            return payment;
            
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            StructuredLogger.setDuration(duration);
            StructuredLogger.setError("MERCADOPAGO_PAYMENT_FAILED", e.getMessage());
            logger.error("Failed to create MercadoPago payment: amount={}, duration={}ms", 
                        amount, duration, e);
            throw new DomainException("Error in createPaymentOrder: " + e.getMessage());
        } finally {
            StructuredLogger.clear();
        }
    }

    @Override
    public PaymentStatusResponse getPaymentById(String paymentId) {
        String url = "https://api.mercadopago.com/v1/payments/" + paymentId;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<PaymentStatusResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, PaymentStatusResponse.class
        );
        return response.getBody();
    }

}
