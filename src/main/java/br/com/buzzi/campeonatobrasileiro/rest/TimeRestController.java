package br.com.buzzi.campeonatobrasileiro.rest;

import br.com.buzzi.campeonatobrasileiro.entity.Time;
import br.com.buzzi.campeonatobrasileiro.service.TimeServico;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "times")
public class TimeRestController {

    @Autowired
    private TimeServico timeServico;

    @GetMapping
    public ResponseEntity<List<Time>> getTimes(){
        return ResponseEntity.ok().body(timeServico.listarTimes());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Time> getTime (@PathVariable Integer id){
        return ResponseEntity.ok().body(timeServico.obterTime(id));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarTime(Time time){
        timeServico.cadastrarTime(time);
        return ResponseEntity.ok().build();
    }
}
