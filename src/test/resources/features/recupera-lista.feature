Feature: recupera lista de votos

  Scenario: Deve recuperar uma lista de Votos registrados no banco de dados
    Given o banco de dados estar limpo
    When eu cadastro os votos com os nomes ["Test1", "Test2", "test3"] e os numeros dos candidadtos [9998, 9997, 9996]
    Then eu consulto a lista de nomes e conditatos registrados no banco de dados