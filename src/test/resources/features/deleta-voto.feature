Feature: Remove o voto

  Scenario: Deve deletar o voto que foi registrado no banco de dados
    Given o banco de dados estar limpo
    When eu cadastro o voto com o nome "Test" e numero do candidadto 9996
    And eu removo do banco de dados o voto com o nome "Test"
    Then o banco de dados não deve conter o mais o voto com o nome "Test"