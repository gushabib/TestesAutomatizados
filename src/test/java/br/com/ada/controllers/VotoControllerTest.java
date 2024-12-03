package br.com.ada.controllers;

import br.com.ada.entities.Voto;
import br.com.ada.service.VotoService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VotoControllerTest {

    @InjectMocks
    private VotoController votoController;

    @Mock
    private VotoService votoService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(votoController).build();
    }


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
                .andExpect(status().isOk());


    }




}
