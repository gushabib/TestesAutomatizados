package br.com.ada.controllers;

import br.com.ada.entities.Voto;
import br.com.ada.service.ListaVotoResponse;
import br.com.ada.service.VotoService;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VotoController.class)
class VotoControllerWebMvcTest {

    @MockBean
    private VotoService votoService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Registra voto quando passar dados corretamente")
    void test1() throws Exception {

        Voto voto = new Voto();
        voto.setNome("Teste");
        voto.setNumeroCandidato(9991L);

        when(votoService.registrarVoto(Mockito.any(Voto.class)))
                .thenReturn("voto registrado com sucesso");

        JSONObject votoJson = new JSONObject(voto.toString());

        mockMvc.perform(post("/voto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(votoJson.toString()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("voto registrado com sucesso"));
    }

    @Test
    @DisplayName("Obter voto quando id do voto eh passado corretamente")
    void test2() throws Exception {

        Voto voto = new Voto();
        voto.setId(1L);
        voto.setNome("Teste");
        voto.setNumeroCandidato(9991L);

        when(votoService.getVotoPorId(1l)).thenReturn(voto);

        mockMvc.perform(get("/voto/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Teste"))
                .andExpect(jsonPath("$.numeroCandidato").value(9991));

    }

    @Test
    @DisplayName("Obter uma lista de votos cadastrados")
    void test3() throws Exception {

        Voto voto = new Voto();
        voto.setId(1L);
        voto.setNome("Teste");
        voto.setNumeroCandidato(9991L);

        Voto voto1 = new Voto();
        voto1.setId(2L);
        voto1.setNome("Teste");
        voto1.setNumeroCandidato(9991L);

        Voto voto2 = new Voto();
        voto2.setId(1L);
        voto2.setNome("Teste");
        voto2.setNumeroCandidato(9991L);

        List<Voto> listaDeVotos = List.of(voto, voto1, voto2);
        when(votoService.obterTodosOsVotos()).thenReturn(new ListaVotoResponse(listaDeVotos));

        mockMvc.perform(get("/votos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[0].nome").value("Teste"))
                .andExpect(jsonPath("$.[2].numeroCandidato").value(9991));
    }




}
