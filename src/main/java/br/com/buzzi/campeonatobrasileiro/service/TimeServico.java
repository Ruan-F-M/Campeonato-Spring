package br.com.buzzi.campeonatobrasileiro.service;

import br.com.buzzi.campeonatobrasileiro.dto.TimeDTO;
import br.com.buzzi.campeonatobrasileiro.entity.Time;
import br.com.buzzi.campeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeServico {
    @Autowired
    private TimeRepository timeRepository;

    public TimeDTO cadastrarTime(TimeDTO time) throws Exception {
        Time entity = toEntity(time);

        if (time.getId() == null) {
            Integer newId = Math.toIntExact(timeRepository.count() + 1);
            time.setId(newId);
            entity = timeRepository.save(entity);
            return toDto(entity);
        } else {
            throw new Exception("Time já existe.");
        }
    }

    private Time toEntity(TimeDTO time) {
        Time entity = new Time();
        entity.setId(time.getId());
        entity.setEstadio(time.getEstadio());
        entity.setSigla(time.getSigla());
        entity.setNome(time.getNome());
        entity.setUf(time.getUf());
        return entity;
    }

    public TimeDTO toDto(Time entity) {
        TimeDTO dto = new TimeDTO();
        dto.setId(entity.getId());
        dto.setEstadio(entity.getEstadio());
        dto.setSigla(entity.getSigla());
        dto.setNome(entity.getNome());
        dto.setUf(entity.getUf());
        return dto;
    }

    public List<TimeDTO> listarTimes() {
        return timeRepository.findAll().stream().map(entity -> toDto(entity)).collect(Collectors.toList());
        /*List<TimeDTO> timesDtos = new ArrayList<>();
        final List<Time> all = repository.findAll();
        all.forEach(time -> {
            timesDtos.add(toDto(time));
        });
        return timesDtos;*/

    }

    public TimeDTO obterTime(Integer id) {
        return toDto(timeRepository.findById(id).get());
    }

    public List<Time> findAll() {
        return timeRepository.findAll();
    }
}
