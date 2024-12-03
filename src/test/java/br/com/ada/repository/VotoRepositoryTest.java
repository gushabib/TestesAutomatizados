package br.com.ada.repository;

import br.com.ada.entities.Voto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
//@DataJpaTest
@SpringBootTest
class VotoRepositoryTest {

    @Autowired
    private VotoRepository votoRepository;

//    @BeforeEach
//    @AfterEach
//    void limparBanco(){
//        System.out.println("Limpando o banco...");
//        votoRepository.deleteAll();
//    }

    @Test
    @DisplayName("Deve registrar voto corretamente no banco de dados")
    void test01() {
        System.out.println("Iniciando teste01");
        Voto voto = new Voto();
        voto.setNome("Test1");
        voto.setNumeroCandidato(8525L);
        Voto votoSalvo = votoRepository.save(voto);

        assertEquals("Test1", votoSalvo.getNome());
    }

    @Test
    @DisplayName("Deve buscar um voto registrado no banco de dados")
    void test02() {
        System.out.println("Iniciando teste02");
        Voto voto = new Voto();
        voto.setNome("Test2");
        voto.setNumeroCandidato(8825L);
        Voto votoSalvo = votoRepository.save(voto);

        Optional<Voto> votoRecuperado = votoRepository.findById(votoSalvo.getId());
        assertTrue(votoRecuperado.isPresent());
        assertEquals("Test2", votoRecuperado.get().getNome());
        assertEquals(8825, votoRecuperado.get().getNumeroCandidato());
    }

    @Test
    @DisplayName("Deve buscar vários votos registrados no banco de dados")
    void test03() {
        System.out.println("Iniciando teste03");
        Voto voto1 = new Voto();
        voto1.setNome("Voto1");
        voto1.setNumeroCandidato(7888L);
        Voto votoSalvo1 = votoRepository.save(voto1);

        Voto voto2 = new Voto();
        voto2.setNome("Voto2");
        voto2.setNumeroCandidato(8888L);
        Voto votoSalvo2 = votoRepository.save(voto2);

        Voto voto3 = new Voto();
        voto3.setNome("Voto3");
        voto3.setNumeroCandidato(9888L);
        Voto votoSalvo3 = votoRepository.save(voto3);

        List<Voto> listaDeVotos = votoRepository.findAll();

        System.out.println(listaDeVotos);
        assertAll(() -> {
            assertEquals("Voto1", listaDeVotos.get(0).getNome());
            assertEquals(3, listaDeVotos.size());
            assertEquals(8888, listaDeVotos.get(1).getNumeroCandidato());
            assertEquals("Voto3", listaDeVotos.get(2).getNome());
            }
        );
    }

}