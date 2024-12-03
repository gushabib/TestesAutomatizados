Feature: Atualiza voto

  Scenario: Deve atualizar um usuário registrado no banco de dados
    Given o banco de dados estar limpo
    When eu cadastro o voto com o nome "Test" e numero do candidadto 9996
    And eu atualizo com o voto com o nome "Test" para "Test1"
    Then o voto com nome "Test1" deve estar salvo no banco de dados