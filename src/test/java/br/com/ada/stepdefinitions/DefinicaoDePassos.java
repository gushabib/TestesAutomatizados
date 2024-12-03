package br.com.ada.stepdefinitions;

import br.com.ada.entities.Voto;
import br.com.ada.repository.VotoRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest
public class DefinicaoDePassos {

    @Autowired
    VotoRepository votoRepository;
    @Given("o banco de dados estar limpo")
    public void oBancoDeDadosEstaLimpo(){
        votoRepository.deleteAll();
    }

    @When("eu cadastro o voto com o nome {string} e numero do candidadto {int}")
    public void euCadastroOVotoComONomeENumeroDoCandidadto(String nome, int numeroCandidato){
        Voto voto = new Voto();
        voto.setNome(nome);
        voto.setNumeroCandidato((long) numeroCandidato);
        votoRepository.save(voto);
    }

    @Then("o voto com nome {string} deve estar salvo no banco de dados")
    public void oVotoComONomeDeveEstarSalvoNoBancoDeDados(String nome){
        Optional<Voto> votoOptional = votoRepository.findVotoByNome(nome);
        Voto voto = votoOptional.get();
        assertEquals(nome, voto.getNome());
    }


    @And("eu atualizo com o voto com o nome {string} para {string}")
    public void euAtualizoComOVotoComONomePara(String nomeAntigo, String nomeNovo) {
        Optional<Voto> votoOptional = votoRepository.findVotoByNome(nomeAntigo);
        Voto voto = votoOptional.get();
        voto.setNome(nomeNovo);
        votoRepository.save(voto);
    }

    @And("eu removo do banco de dados o voto com o nome {string}")
    public void euRemovoDoBancoDeDadosOVotoComONome(String nome) {
        Optional<Voto> votoOptional = votoRepository.findVotoByNome(nome);
        if(votoOptional.isPresent()) votoRepository.delete(votoOptional.get());
    }

    @Then("o banco de dados não deve conter o mais o voto com o nome {string}")
    public void oBancoDeDadosNãoDeveConterOMaisOVotoComONome(String nome) {
        Optional<Voto> votoOptional = votoRepository.findVotoByNome(nome);
        assertTrue(votoOptional.isEmpty());
    }
}