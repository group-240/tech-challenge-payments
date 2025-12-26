package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;
import java.util.Map;

@Data
public class TransactionData {

    private String qr_code;
    private String qr_code_base64;
    private String bank_transfer_id;
    private String transaction_id;
    private String e2e_id;
    private String financial_institution;
    private String ticket_url;
    private String merchant_category_code;
    private Boolean is_end_consumer;

    private BankInfo bank_info;

    @Data
    public static class BankInfo {
        private Map<String, Object> payer;
        private Map<String, Object> collector;
        private Boolean is_same_bank_account_owner;
        private String origin_bank_id;
        private String origin_wallet_id;
    }
}
