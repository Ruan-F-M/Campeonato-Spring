package br.com.buzzi.campeonatobrasileiro.rest;

import br.com.buzzi.campeonatobrasileiro.dto.JogoDTO;
import br.com.buzzi.campeonatobrasileiro.entity.Jogo;
import br.com.buzzi.campeonatobrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok().body(jogoService.listarJogos());
    }

    @PostMapping(value = "/finalizar/{id}")
    public ResponseEntity<JogoDTO> finalizar(@PathVariable Integer id, @RequestBody JogoDTO jogoDto) throws Exception {
        return ResponseEntity.ok().body(jogoService.finalizar(id, jogoDto));
    }

//    @GetMapping(value = "/classificacao")
//    public ResponseEntity<classificacaoDTO> classificacao () {
//        return ResponseEntity.ok().body(jogoService.obterClassificacao());
//    }
    @GetMapping(value = "/jogo/{id}")
    public ResponseEntity<JogoDTO> obterJogo (@PathVariable Integer id){
        return ResponseEntity.ok().body(jogoService.obterJogo(id));
    }
}
