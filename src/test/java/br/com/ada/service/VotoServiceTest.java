package br.com.ada.service;

import br.com.ada.entities.Voto;
import br.com.ada.repository.VotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Test
    @DisplayName("Deve registrar voto quando dados estiverem corretos")
    void test1() {

        VotoService votoService = new VotoService(new VotoRepositoryFakeImpl());


        Voto voto = new Voto();
        voto.setNome("teste");
        voto.setNumeroCandidato(9991L);
        String resultado = votoService.registrarVoto(voto);

        assertEquals("voto registrado com sucesso", resultado);

    }


    @Test
    @DisplayName("Deve registrar voto quando dados estiverem corretos com Mockito")
    void test2() {



        Voto voto = new Voto();
        voto.setNome("teste");
        voto.setNumeroCandidato(9991L);

        //programacao do mockito deve ser feita antes da chamada do metodo da classe
        Mockito.when(votoRepository.save(voto)).thenReturn(voto);

        String resultado = votoService.registrarVoto(voto);
        assertEquals("voto registrado com sucesso", resultado);

    }

}
