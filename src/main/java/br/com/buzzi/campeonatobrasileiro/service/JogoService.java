package br.com.buzzi.campeonatobrasileiro.service;

import br.com.buzzi.campeonatobrasileiro.dto.JogoDTO;
import br.com.buzzi.campeonatobrasileiro.entity.Jogo;
import br.com.buzzi.campeonatobrasileiro.entity.Time;
import br.com.buzzi.campeonatobrasileiro.repository.JogoRepository;
import br.com.buzzi.campeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JogoService {

    @Autowired
    JogoRepository jogoRepository;
    @Autowired
    TimeRepository timeServico;

    public void gerarJogos(LocalDateTime primeiraRodada) {
        final List<Time> times = timeServico.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();
        all1.addAll(times);
        all2.addAll(times);

        jogoRepository.deleteAll();

        List<Jogo> jogos = new ArrayList<>();

        int t = times.size();
        int m = times.size();
        LocalDateTime dataJogo = primeiraRodada;
        Integer rodada = 0;
        for (int i = 0; i < t -1; i++){
            rodada = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando do campo
                Time time1;
                Time time2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    time1 = times.get(t - j -1);
                    time2 = times.get(j);
                }else {
                    time1 = times.get(j);
                    time2 = times.get(t - j - 1);
                }
                if (time1 == null) {
                    System.out.println("Time 1 null");
                }
                jogos.add(gerarJogo(dataJogo, rodada, time1, time2));
                dataJogo = dataJogo.plusDays(7);
            }
            //Gira os times no sentido horário, mantendo o primeiro no lugar
            times.add(1, times.remove(times.size() - 1));
        }
        jogos.forEach(jogo -> System.out.println(jogo));

        jogoRepository.saveAll(jogos);

        List<Jogo> jogos2 = new ArrayList<>();

        jogos.forEach(jogo -> {
            Time time1 = jogo.getTime2();
            Time time2 = jogo.getTime1();
            jogos2.add(gerarJogo(jogo.getData().plusDays(7 * jogos.size()), jogo.getRodada() + jogos.size(), time1, time2));
        });
        jogoRepository.saveAll(jogos2);
    }

    private Jogo gerarJogo(LocalDateTime dataJogo, Integer rodada, Time time1, Time time2) {
        Jogo jogo = new Jogo();
        jogo.setTime1(time1);
        jogo.setTime1(time1);
        jogo.setRodada(rodada);
        jogo.setData(dataJogo);
        jogo.setEncerrado(false);
        jogo.setGolsTime1(0);
        jogo.setGolsTime2(0);
        jogo.setPublicoPagante(0);
        return jogo;
    }

    private JogoDTO entityToDTO(Jogo entity) {
        JogoDTO dto = new JogoDTO();
        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setEncerrado(entity.getEncerrado());
        dto.setGolsTime1(entity.getGolsTime1());
        dto.setGolsTime2(entity.getGolsTime2());
        dto.setPublicoPagante(entity.getPublicoPagante());
        dto.setRodada(entity.getRodada());
        dto.setTime1(timeServico.toDto(entity.getTime1()));
        dto.setTime2(timeServico.toDto(entity.getTime2()));
        return dto;
    }

    public List<Jogo> obterJogos(){
        return jogoRepository.findAll();
    }
}
