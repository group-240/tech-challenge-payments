package com.fiap.techchallenge.external.datasource.repositories;

import com.fiap.techchallenge.external.datasource.entities.OrderJpaEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderJpaRepository {

    private final DynamoDbTable<OrderJpaEntity> orderTable;

    public OrderJpaRepository(DynamoDbClient dynamoDbClient,
                              @Value("${dynamodb.table.name:tech-challenge-orders}") String tableName) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.orderTable = enhancedClient.table(tableName, TableSchema.fromBean(OrderJpaEntity.class));
    }

    public OrderJpaEntity save(OrderJpaEntity entity) {
        orderTable.putItem(entity);
        return entity;
    }

    public Optional<OrderJpaEntity> findById(Long id) {
        Key key = Key.builder().partitionValue(id).build();
        return Optional.ofNullable(orderTable.getItem(key));
    }

    public Optional<OrderJpaEntity> findByIdPayment(Long idPayment) {
        // Scan to find by idPayment (consider using GSI for production)
        return orderTable.scan(ScanEnhancedRequest.builder().build())
                .items()
                .stream()
                .filter(item -> idPayment.equals(item.getIdPayment()))
                .findFirst();
    }

    public List<OrderJpaEntity> findAll() {
        List<OrderJpaEntity> results = new ArrayList<>();
        orderTable.scan().items().forEach(results::add);
        return results;
    }

    public void deleteById(Long id) {
        Key key = Key.builder().partitionValue(id).build();
        orderTable.deleteItem(key);
    }
}
