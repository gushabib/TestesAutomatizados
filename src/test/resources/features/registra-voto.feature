Feature: Registro de voto

  Scenario: Deve registrar voto corretamente quando os dados forem enviados
    Given o banco de dados estar limpo
    When eu cadastro o voto com o nome "Test" e numero do candidadto 9996
    Then o voto com nome "Test" deve estar salvo no banco de dados