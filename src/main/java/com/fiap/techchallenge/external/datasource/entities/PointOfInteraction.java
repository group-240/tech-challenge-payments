package com.fiap.techchallenge.external.datasource.entities;

import lombok.Data;

@Data
public class PointOfInteraction {

    private String type;
    private BusinessInfo business_info;
    private Location location;
    private ApplicationData application_data;
    private TransactionData transaction_data;

    @Data
    public static class BusinessInfo {
        private String unit;
        private String sub_unit;
        private String branch;
    }

    @Data
    public static class Location {
        private String state_id;
        private String source;
    }

    @Data
    public static class ApplicationData {
        private String name;
        private String operating_system;
        private String version;
    }
}
