package com.fiap.techchallenge.external.datasource.mercadopago;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${dynamodb.payments.table.name:tech-challenge-payments}")
    private String paymentsTableName;

    public Map<String, AttributeValue> savePaymentJson(String json) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(UUID.randomUUID().toString()).build());
        item.put("payload", AttributeValue.builder().s(json).build());
        item.put("createdAt", AttributeValue.builder().s(java.time.Instant.now().toString()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(paymentsTableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
        return item;
    }

    public PaymentResponse parsePayment(String json) {
        try {
            return objectMapper.readValue(json, PaymentResponse.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao converter JSON do Mercado Pago", e);
        }
    }
}
