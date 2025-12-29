package com.fiap.techchallenge.external.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaymentStatusResponse {
    @Schema(example = "1325737896", description = "ID do pagamento")
    private String id;

    @Schema(
            example = "pending",
            description = "É o estado atual do pagamento. Valores possíveis:\n" +
                    "pending: O usuário ainda não concluiu o processo de pagamento.\n" +
                    "approved: O pagamento foi aprovado e creditado com sucesso.\n" +
                    "authorized: O pagamento foi autorizado, mas ainda não foi capturado.\n" +
                    "in_process: O pagamento está em análise.\n" +
                    "in_mediation: O usuário iniciou uma disputa.\n" +
                    "rejected: O pagamento foi rejeitado (o usuário pode tentar pagar novamente).\n" +
                    "cancelled: O pagamento foi cancelado por uma das partes ou expirou.\n" +
                    "refunded: O pagamento foi reembolsado ao usuário.\n" +
                    "charged_back: Um chargeback foi aplicado no cartão de crédito do comprador.",
            allowableValues = {
                    "pending", "approved", "authorized", "in_process", "in_mediation",
                    "rejected", "cancelled", "refunded", "charged_back"
            }
    )
    private String status;

    @Schema(example = "pending_waiting_transfer", description = "Detalhe do status do pagamento")
    private String status_detail;

    @Schema(example = "1.00", description = "Valor da transação")
    private Double transaction_amount;

    @Schema(example = "Test payment", description = "Descrição do pagamento")
    private String description;

    @Schema(example = "pix", description = "Método de pagamento", allowableValues = { "Ted", "pix", "CVU", "PSE"})
    private String payment_method_id;

    @Schema(example = "teste@email.com", description = "E-mail do pagador", nullable = true)
    private String payer_email;
    // getters e setters
}