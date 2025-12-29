package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class FeeDetail {

    private String type;
    private BigDecimal amount;
    private String fee_payer;
}
