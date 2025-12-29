package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Data
public class PaymentResponse {

    private Long id;
    private OffsetDateTime date_created;
    private OffsetDateTime date_approved;
    private OffsetDateTime date_last_updated;
    private OffsetDateTime date_of_expiration;
    private OffsetDateTime money_release_date;

    private String money_release_status;
    private String operation_type;
    private String issuer_id;
    private String payment_method_id;
    private String payment_type_id;

    private PaymentMethod payment_method;

    private String status;
    private String status_detail;
    private String currency_id;
    private String description;

    private Boolean live_mode;
    private Long sponsor_id;
    private String authorization_code;
    private String money_release_schema;

    private BigDecimal taxes_amount;
    private String counter_currency;
    private String brand_id;
    private BigDecimal shipping_amount;

    private String build_version;
    private String pos_id;
    private String store_id;
    private String integrator_id;
    private String platform_id;
    private String corporation_id;

    private ChargesExecutionInfo charges_execution_info;
    private Payer payer;

    private Long collector_id;
    private String marketplace_owner;

    private Map<String, Object> metadata;
    private AdditionalInfo additional_info;
    private Map<String, Object> order;

    private String external_reference;

    private BigDecimal transaction_amount;
    private BigDecimal transaction_amount_refunded;
    private BigDecimal coupon_amount;

    private String differential_pricing_id;
    private String financing_group;
    private String deduction_schema;
    private String callback_url;

    private Integer installments;

    private TransactionDetails transaction_details;

    private List<FeeDetail> fee_details;
    private List<ChargeDetail> charges_details;

    private Boolean captured;
    private Boolean binary_mode;
    private String call_for_authorize_id;
    private String statement_descriptor;

    private Map<String, Object> card;

    private String notification_url;

    private List<Object> refunds;

    private String processing_mode;
    private String merchant_account_id;
    private String merchant_number;

    private List<Object> acquirer_reconciliation;

    private PointOfInteraction point_of_interaction;

    private String accounts_info;
    private String release_info;
    private List<String> tags;
}

