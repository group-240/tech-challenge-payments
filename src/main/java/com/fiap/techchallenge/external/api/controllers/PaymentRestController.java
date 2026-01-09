package com.fiap.techchallenge.external.api.controllers;

import com.fiap.techchallenge.adapters.controllers.PaymentController;
import com.fiap.techchallenge.external.api.dto.PaymentOrderRequest;
import com.fiap.techchallenge.external.api.dto.PaymentStatusResponse;
import com.fiap.techchallenge.external.datasource.entities.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payments", description = "API para pagamento")
public class PaymentRestController {

    private final PaymentController paymentController;

    public PaymentRestController(PaymentController paymentController) {
        this.paymentController = paymentController;
    }

    @PostMapping
    @Operation(summary = "Cria um pagamento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ordem de pagamento criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PaymentResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro na requisição, como parâmetros inválidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody PaymentOrderRequest request) {
        try {
            if (request.getAmount() == null ||
                    request.getPaymentMethodId() == null || request.getInstallments() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            PaymentResponse result = paymentController.createPaymentOrder(
                    request.getAmount(),
                    request.getDescription(),
                    request.getPaymentMethodId(),
                    request.getInstallments(),
                    request.getPayerEmail(),
                    request.getIdentificationType(),
                    request.getIdentificationNumber()
            );

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Consulta um pagamento no Mercado Pago")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso na consulta do pagamento",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PaymentStatusResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro na requisição, como parâmetros inválidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "ID do pagamento", example = "1325737896")
    public ResponseEntity<PaymentStatusResponse> getPaymentById(@PathVariable String id) {
        PaymentStatusResponse response = paymentController.getPaymentById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

}