package br.com.buzzi.campeonatobrasileiro.service;

import br.com.buzzi.campeonatobrasileiro.entity.Time;
import br.com.buzzi.campeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class TimeServico {
    @Autowired
    private TimeRepository repository;


    public void cadastrarTime(Time time){
        repository.save(time);
    }

    public List<Time> listarTimes(){
        return repository.findAll();
    }
    public Time obterTime(Integer id){
        return repository.findById(id).get();
    }

}
