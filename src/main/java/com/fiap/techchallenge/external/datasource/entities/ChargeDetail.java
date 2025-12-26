package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Data
public class ChargeDetail {

    private String id;
    private String name;
    private String type;
    private Accounts accounts;
    private Long client_id;

    private OffsetDateTime date_created;
    private OffsetDateTime last_updated;

    private Amounts amounts;
    private Map<String, String> metadata;

    private String reserve_id;
    private List<Object> refund_charges;
    private String external_charge_id;
    private List<Object> update_charges;

    @Data
    public static class Accounts {
        private String from;
        private String to;
    }

    @Data
    public static class Amounts {
        private BigDecimal original;
        private BigDecimal refunded;
    }
}

