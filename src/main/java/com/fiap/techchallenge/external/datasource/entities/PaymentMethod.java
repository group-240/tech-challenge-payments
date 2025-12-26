package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;

@Data
public class PaymentMethod {
    private String id;
    private String type;
    private String issuer_id;
}

