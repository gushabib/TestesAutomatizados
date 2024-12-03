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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @When("eu cadastro os votos com os nomes [{string}, {string}, {string}] e os numeros dos candidadtos [{int}, {int}, {int}]")
    public void euCadastroOsVotosComOsNomesEOsNumerosDosCandidadtos(String nome1, String nome2, String nome3, int numeroCandidato1, int numeroCandidato2, int numeroCandidato3) {
        Voto voto1 = new Voto();
        voto1.setNome(nome1);
        voto1.setNumeroCandidato((long) numeroCandidato1);

        Voto voto2 = new Voto();
        voto2.setNome(nome2);
        voto2.setNumeroCandidato((long) numeroCandidato2);

        Voto voto3 = new Voto();
        voto3.setNome(nome3);
        voto3.setNumeroCandidato((long) numeroCandidato3);

        votoRepository.save(voto1);
        votoRepository.save(voto2);
        votoRepository.save(voto3);
    }

    @Then("eu consulto a lista de nomes e conditatos registrados no banco de dados")
    public void euConsultoAListaDeNomesEConditatosRegistradosNoBancoDeDados() {
        List<Voto> listVoto = votoRepository.findAll();
        assertFalse(listVoto.isEmpty());
        assertEquals(3, listVoto.size());
        assertEquals("Test2", listVoto.get(1).getNome());

    }
}