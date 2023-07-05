package br.com.buzzi.campeonatobrasileiro.rest;

import br.com.buzzi.campeonatobrasileiro.dto.JogoDTO;
import br.com.buzzi.campeonatobrasileiro.entity.Jogo;
import br.com.buzzi.campeonatobrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/jogos")
public class JogoRestController {

    @Autowired
    private JogoService jogoService;

    @PostMapping(value = "/gerar-jogos")
    public ResponseEntity<Void> gerarJogos(){
        jogoService.gerarJogos(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JogoDTO>> obterJogos(){
        jogoService.gerarJogos(LocalDateTime.now());
        return ResponseEntity.ok().body(jogoService.obterJogos());
    }
}
