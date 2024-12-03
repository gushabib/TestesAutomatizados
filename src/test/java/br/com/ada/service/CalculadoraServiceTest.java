package br.com.ada.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraServiceTest {


    @Test
    @DisplayName("deve realizar a soma de numeros inteiros maiores que zero")
    void test1() {
        var calculadoraService = new CalculadoraService();
        int resultado = calculadoraService.soma(1, 1);
        assertEquals(1,
                resultado,
                ()-> "resultado correto");

    }

    @Test
    @DisplayName("Deve lancar excecao quando algum dos numeros forem menores que zero")
    void test2() {
        var calculadoraService = new CalculadoraService();
        assertThrows(IllegalArgumentException.class,
                () -> calculadoraService.soma(-1, -1),
                () -> "entrada invalida");

    }
}
