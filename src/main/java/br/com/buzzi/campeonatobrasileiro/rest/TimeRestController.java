package br.com.buzzi.campeonatobrasileiro.rest;

import br.com.buzzi.campeonatobrasileiro.dto.TimeDTO;
import br.com.buzzi.campeonatobrasileiro.entity.Time;
import br.com.buzzi.campeonatobrasileiro.service.TimeServico;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/times")
public class TimeRestController {

    @Autowired
    private TimeServico timeServico;

    @GetMapping
    public ResponseEntity<List<TimeDTO>> getTimes(){
        return ResponseEntity.ok().body(timeServico.listarTimes());
    }

    @ApiOperation(value = "Obtém os dados um time")
    @GetMapping(value = "{id}")
    public ResponseEntity<TimeDTO> getTime (@PathVariable Integer id){
        return ResponseEntity.ok().body(timeServico.obterTime(id));
    }

    @PostMapping
    public ResponseEntity<TimeDTO> cadastrarTime(@Valid @RequestBody TimeDTO time) throws Exception {
        timeServico.cadastrarTime(time);
        return ResponseEntity.ok().body(timeServico.cadastrarTime(time));
    }
}
