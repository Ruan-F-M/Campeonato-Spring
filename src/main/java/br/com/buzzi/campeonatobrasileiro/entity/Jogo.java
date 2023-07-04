package br.com.buzzi.campeonatobrasileiro.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Jogo {
    private Time time1;
    private Time time2;
    private Integer golsTime1;
    private Integer golsTime2;
    private Integer publicoPagante
}
