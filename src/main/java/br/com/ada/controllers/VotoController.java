package br.com.ada.controllers;

import br.com.ada.entities.Voto;
import br.com.ada.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class VotoController {


    private VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/voto")
    public ResponseEntity<String> registraVoto(@RequestBody Voto voto) {
        String resultado = votoService.registrarVoto(voto);
        return ResponseEntity.created(URI.create("test")).body(resultado);
    }

    @GetMapping("/voto/{id}")
    public ResponseEntity<Voto> obterVoto(@PathVariable long id){
        Voto voto = votoService.getVotoPorId(id);
        return ResponseEntity.ok(voto);
    }


    @GetMapping("/votos")
    public ResponseEntity<List<Voto>> obterTodosOsVotos() {
        return ResponseEntity.ok(votoService.obterTodosOsVotos());
    }

    @PutMapping("/voto/{id}")
    public ResponseEntity<Voto> atualiza(@PathVariable Long id, @RequestBody Voto voto) {
       return ResponseEntity.ok(votoService.atualizarVoto(id, voto));
    }

    @DeleteMapping("/voto/delete/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        return ResponseEntity.ok(votoService.deletarVoto(id));
    }

}
