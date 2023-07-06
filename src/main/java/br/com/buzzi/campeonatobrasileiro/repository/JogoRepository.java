package br.com.buzzi.campeonatobrasileiro.repository;

import br.com.buzzi.campeonatobrasileiro.entity.Jogo;
import br.com.buzzi.campeonatobrasileiro.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    //SELECT * FROM JOGO WHERE TIME! + :TIME! AND ENCERRADO + :ENCERRADO
    List<Jogo> findByTime1AndEncerrado(Time time1, Boolean encerrado);
    List<Jogo> findByTime2AndEncerrado(Time time2, Boolean encerrado);

}