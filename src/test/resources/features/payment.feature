Funcionalidade: Pagamento

  Cenário: Consulta de pagamento existente
    Dado que existe um pagamento com id "123"
    Quando eu consulto o pagamento pelo id "123"
    Então o status da resposta deve ser "approved"

  Cenário: Consulta de pagamento inexistente
    Dado que não existe um pagamento com id "999"
    Quando eu consulto o pagamento pelo id "999"
    Então a resposta deve ser 404

