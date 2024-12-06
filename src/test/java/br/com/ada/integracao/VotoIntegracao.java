package br.com.ada.integracao;

import br.com.ada.entities.Voto;
import br.com.ada.service.ListaVotoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VotoIntegracao {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve registrar voto corretamente quando os dados forem enviados")
    void test01() {
        Voto voto = new Voto();
        voto.setNome("Voto1");
        voto.setNumeroCandidato(1l);

        ResponseEntity<String> ResponseEntity = restTemplate.postForEntity("/voto", voto, String.class);

        assertEquals("voto registrado com sucesso", ResponseEntity.getBody());
    }

    @Test
    @DisplayName("Deve obter um voto registrado anteriormente pelo ID")
    void test02() {
        Voto voto = new Voto();
        voto.setNome("Voto2");
        voto.setNumeroCandidato(2l);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/voto", voto, String.class);

        assertEquals("voto registrado com sucesso", responseEntity.getBody());

        ResponseEntity<Voto> votoResponseEntity = restTemplate.getForEntity("/voto/1", Voto.class);

        assertEquals("Voto2", votoResponseEntity.getBody().getNome());
    }

    @Test
    @DisplayName("Deve atualizar um usuário registrado no banco de dados")
    void test03() {
        Voto voto = new Voto();
        voto.setNome("Voto5");
        voto.setNumeroCandidato(2l);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/voto", voto, String.class);

        Voto atualizaVoto = new Voto();
        atualizaVoto.setNome("Voto6");
        atualizaVoto.setNumeroCandidato(3l);

        // restTemplate.put("/voto/1", atualizaVoto);

        HttpEntity<Voto> httpEntity = new HttpEntity<>(atualizaVoto);
        ResponseEntity<Voto> exchangeAtualizaVoto = restTemplate.exchange("/voto/1", HttpMethod.PUT, httpEntity, Voto.class);
        assertNotNull(exchangeAtualizaVoto.getBody());
        assertEquals("Voto6", exchangeAtualizaVoto.getBody().getNome());


    }

    @Test
    @DisplayName("Deve consultar uma lista de votos")
    void test04() {
        Voto[] votos = new Voto[3];

        votos[0] = new Voto();
        votos[0].setNome("Voto1");
        votos[0].setNumeroCandidato(1L);

        votos[1] = new Voto();
        votos[1].setNome("Voto2");
        votos[1].setNumeroCandidato(2L);

        votos[2] = new Voto();
        votos[2].setNome("Voto3");
        votos[2].setNumeroCandidato(3L);

        for (Voto voto : votos) {
            ResponseEntity<String> ResponseEntity = restTemplate.postForEntity("/voto", voto, String.class);
        }

        ResponseEntity<ListaVotoResponse> retornoVotos = restTemplate.getForEntity("/votos", ListaVotoResponse.class);

        System.out.println(retornoVotos.getBody());

        assertNotNull(retornoVotos.getBody());

        List<Voto> body =retornoVotos.getBody().listaVotos();

        assertEquals(votos[0].getNome(), body.get(0).getNome());
    }



    @Test
    @DisplayName("Deve deletar um voto registrado no banco de dados")
    void test05() {
        Voto voto = new Voto();
        voto.setNome("Voto5");
        voto.setNumeroCandidato(2l);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/voto", voto, String.class);

        // restTemplate.delete("/voto/delete/1");
        HttpEntity<Object> objectHttpEntity = new HttpEntity<>(null);
        var responseDeleteEntity = restTemplate.exchange("/voto/delete/1", HttpMethod.DELETE, objectHttpEntity, String.class);
        assertNotNull(responseDeleteEntity.getBody());
        assertEquals("Voto de id 1 deletado com sucesso", responseDeleteEntity.getBody());
    }

}