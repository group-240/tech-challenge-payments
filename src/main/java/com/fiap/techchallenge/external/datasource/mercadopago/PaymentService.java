package com.fiap.techchallenge.external.datasource.mercadopago;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private  ObjectMapper objectMapper;

    public Document savePaymentJson(String json) {
        Document doc = Document.parse(json);
        Document payments = mongoTemplate.save(doc, "payments");
        return payments;
    }

    public PaymentResponse parsePayment(String json) {
        try {
            return objectMapper.readValue(json, PaymentResponse.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao converter JSON do Mercado Pago", e);
        }
    }

}
