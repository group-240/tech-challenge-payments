package com.fiap.techchallenge.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private Long id;
    private UUID customerId;
    private BigDecimal totalAmount;
    private Long idPayment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order() {}

    public Order(Long id, UUID customerId,
                 BigDecimal totalAmount,
                 Long idPayment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.idPayment = idPayment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Factory method

    // Getters
    public Long getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public Long getIdPayment() { return idPayment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
