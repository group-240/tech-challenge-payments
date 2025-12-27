Funcionalidade: PaymentUseCase

  Cenário: Executar caso de uso de pagamento existente
    Dado que existe um pagamento no caso de uso com id "123"
    Quando eu executo o caso de uso de pagamento pelo id "123"
    Então o pagamento do caso de uso deve ser encontrado

  Cenário: Executar caso de uso de pagamento inexistente
    Dado que não existe um pagamento no caso de uso com id "999"
    Quando eu executo o caso de uso de pagamento pelo id "999"
    Então o pagamento do caso de uso não deve ser encontrado

