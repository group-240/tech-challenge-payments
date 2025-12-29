Feature: Payment Controller

  Scenario: Criar ordem de pagamento
    Given que o use case de pagamento retorna sucesso
    When eu crio uma ordem de pagamento
    Then a ordem de pagamento deve ser criada com sucesso

  Scenario: Buscar pagamento por id
    Given que existe um pagamento com id "123"
    When eu busco o pagamento pelo id
    Then o status do pagamento deve ser retornado
