package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;

@Data
public class Payer {

    private Identification identification;
    private String entity_type;
    private Phone phone;
    private String last_name;
    private String id;
    private String type;
    private String first_name;
    private String email;

    @Data
    public static class Identification {
        private String number;
        private String type;
    }

    @Data
    public static class Phone {
        private String number;
        private String extension;
        private String area_code;
    }
}

