package com.fiap.techchallenge.external.api.dto;

import lombok.Data;

@Data
public class PaymentStatusResponse {
    private String id;
    private String status;
    private String status_detail;
    private Double transaction_amount;
    private String description;
    private String payment_method_id;
    private String payer_email;

    // getters e setters
}