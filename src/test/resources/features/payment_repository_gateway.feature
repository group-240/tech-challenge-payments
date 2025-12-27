Funcionalidade: PaymentRepositoryGateway

  Cenário: Buscar pagamento existente no gateway
    Dado que existe um pagamento no gateway com id "123"
    Quando eu busco o pagamento no gateway pelo id "123"
    Então o pagamento do gateway deve ser encontrado

  Cenário: Buscar pagamento inexistente no gateway
    Dado que não existe um pagamento no gateway com id "999"
    Quando eu busco o pagamento no gateway pelo id "999"
    Então o pagamento do gateway não deve ser encontrado

