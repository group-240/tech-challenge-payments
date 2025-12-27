Funcionalidade: PaymentRepository

  Cenário: Buscar pagamento existente no repositório
    Dado que existe um pagamento no repositório com id "123"
    Quando eu busco o pagamento no repositório pelo id "123"
    Então o pagamento do repositório deve ser encontrado

  Cenário: Buscar pagamento inexistente no repositório
    Dado que não existe um pagamento no repositório com id "999"
    Quando eu busco o pagamento no repositório pelo id "999"
    Então o pagamento do repositório não deve ser encontrado

