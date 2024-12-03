package br.com.ada.service;

import br.com.ada.entities.Voto;
import br.com.ada.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {


    private VotoRepository votoRepository;


    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public String registrarVoto(Voto voto) {

       votoRepository.save(voto);

        return "voto registrado com sucesso";

    }

    public Voto getVotoPorId(long id) {

        Optional<Voto> votoOptional = votoRepository.findById(id);

        return votoOptional.orElseThrow(() -> new RuntimeException("Voto nao foi encontrado"));
    }

    public List<Voto> obterTodosOsVotos() {

        return votoRepository.findAll();

    }

    public Voto atualizarVoto(Long id, Voto votoRequest) {
        Voto votoRecuperadoDoBanco = getVotoPorId(id);
        votoRecuperadoDoBanco.setNome(votoRequest.getNome());
        votoRecuperadoDoBanco.setNumeroCandidato(votoRequest.getNumeroCandidato());
        return votoRepository.save(votoRecuperadoDoBanco);
    }

    public String deletarVoto(Long id) {
        Voto votoDeletarBanco = getVotoPorId(id);
        votoRepository.delete(votoDeletarBanco);
        return "Voto de id " + id + " deletado com sucesso";
    }
}
