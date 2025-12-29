package com.fiap.techchallenge.external.datasource.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "orders")
public class OrderJpaEntity {

    @Id
    private Long id;

    private UUID customerId;


    private BigDecimal totalAmount;

    private OrderStatusJpa status;

    private StatusPaymentJpa statusPayment;

    private Long idPayment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public OrderJpaEntity() {}

    // Enums JPA específicos para persistência
    public enum OrderStatusJpa {
        RECEIVED, IN_PREPARATION, READY, FINISHED
    }

    public enum StatusPaymentJpa {
        AGUARDANDO_PAGAMENTO, APROVADO, REJEITADO
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public OrderStatusJpa getStatus() { return status; }
    public void setStatus(OrderStatusJpa status) { this.status = status; }
    public StatusPaymentJpa getStatusPayment() { return statusPayment; }
    public void setStatusPayment(StatusPaymentJpa statusPayment) { this.statusPayment = statusPayment; }
    public Long getIdPayment() { return idPayment; }
    public void setIdPayment(Long idPayment) { this.idPayment = idPayment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
