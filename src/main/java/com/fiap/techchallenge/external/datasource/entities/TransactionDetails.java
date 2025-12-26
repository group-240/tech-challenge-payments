package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionDetails {

    private String payment_method_reference_id;
    private String acquirer_reference;
    private BigDecimal net_received_amount;
    private BigDecimal total_paid_amount;
    private BigDecimal overpaid_amount;
    private String external_resource_url;
    private BigDecimal installment_amount;
    private String financial_institution;
    private String payable_deferral_period;
    private String bank_transfer_id;
    private String transaction_id;
}

