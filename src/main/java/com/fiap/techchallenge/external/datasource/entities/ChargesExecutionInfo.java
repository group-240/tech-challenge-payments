package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class ChargesExecutionInfo {

    private InternalExecution internal_execution;

    @Data
    public static class InternalExecution {
        private OffsetDateTime date;
        private String execution_id;
    }
}
