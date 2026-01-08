package com.fiap.techchallenge.external.datasource.entities;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDbBean
public class OrderJpaEntity {

    private Long id;

    private UUID customerId;

    private BigDecimal totalAmount;

    private String status;

    private String statusPayment;

    private Long idPayment;

    private String createdAt;

    private String updatedAt;

    public OrderJpaEntity() {}

    // Enums JPA específicos para persistência
    public enum OrderStatusJpa {
        RECEIVED, IN_PREPARATION, READY, FINISHED
    }

    public enum StatusPaymentJpa {
        AGUARDANDO_PAGAMENTO, APROVADO, REJEITADO
    }

    // Getters and Setters
    @DynamoDbPartitionKey
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    
    @DynamoDbSecondaryPartitionKey(indexNames = "status-index")
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatusPayment() { return statusPayment; }
    public void setStatusPayment(String statusPayment) { this.statusPayment = statusPayment; }
    public Long getIdPayment() { return idPayment; }
    public void setIdPayment(Long idPayment) { this.idPayment = idPayment; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    
    // Helper methods para converter status
    public void setStatusEnum(OrderStatusJpa statusEnum) { 
        this.status = statusEnum != null ? statusEnum.name() : null; 
    }
    public OrderStatusJpa getStatusEnum() { 
        return status != null ? OrderStatusJpa.valueOf(status) : null; 
    }
    public void setStatusPaymentEnum(StatusPaymentJpa statusPaymentEnum) { 
        this.statusPayment = statusPaymentEnum != null ? statusPaymentEnum.name() : null; 
    }
    public StatusPaymentJpa getStatusPaymentEnum() { 
        return statusPayment != null ? StatusPaymentJpa.valueOf(statusPayment) : null; 
    }
}
