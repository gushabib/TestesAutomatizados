package br.com.ada.service;

public class CalculadoraService {
    public int soma(int numero1, int numero2) {
        if (numero1 < 0 || numero2 < 0) {
            throw new IllegalArgumentException("entrada invalida");
        }
        return numero1 + numero2;
    }
}
