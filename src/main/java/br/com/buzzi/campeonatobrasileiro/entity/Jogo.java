package br.com.buzzi.campeonatobrasileiro.entity;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Time time1;
    private Time time2;
    private Integer golsTime1;
    private Integer golsTime2;
    private Integer publicoPagante;
    private String uuid;
}
