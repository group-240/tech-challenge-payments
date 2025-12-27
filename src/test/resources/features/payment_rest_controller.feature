Funcionalidade: PaymentRestController

  Cenário: Consulta de pagamento existente via REST
    Dado que existe um pagamento via REST com id "123"
    Quando eu consulto o pagamento via REST pelo id "123"
    Então o status da resposta REST deve ser "approved"

  Cenário: Consulta de pagamento inexistente via REST
    Dado que não existe um pagamento via REST com id "999"
    Quando eu consulto o pagamento via REST pelo id "999"
    Então a resposta REST deve ser 404

