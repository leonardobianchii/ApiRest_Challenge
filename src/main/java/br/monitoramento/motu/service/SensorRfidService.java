package br.monitoramento.motu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.monitoramento.motu.dto.SensorRfidDto;
import br.monitoramento.motu.mapper.SensorRfidMapper;
import br.monitoramento.motu.model.FilialDepartamento;
import br.monitoramento.motu.model.SensorRfid;
import br.monitoramento.motu.repository.FilialDepartamentoRepository;
import br.monitoramento.motu.repository.SensorRfidRepository;
import br.monitoramento.motu.exception.ResourceNotFoundException;

@Service
public class SensorRfidService {

    @Autowired private SensorRfidRepository sensorRepo;
    @Autowired private FilialDepartamentoRepository filialRepo;
    @Autowired private SensorRfidMapper mapper;

    public List<SensorRfidDto> findAll() {
        return sensorRepo.findAll().stream().map(mapper::toDto).toList();
    }

    public SensorRfidDto findById(Integer id) {
        return mapper.toDto(buscarOuFalhar(id));
    }

    public SensorRfidDto create(SensorRfidDto dto) {
        FilialDepartamento filial = filialRepo.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento não encontrado"));
        SensorRfid salvo = sensorRepo.save(mapper.toEntity(dto, filial));
        return mapper.toDto(salvo);
    }

    public SensorRfidDto update(Integer id, SensorRfidDto dto) {
        SensorRfid existente = buscarOuFalhar(id);
        FilialDepartamento filial = filialRepo.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento não encontrado"));

        existente.setNome(dto.nome());
        existente.setFilial(filial);
        existente.setLocalizacao(dto.localizacao());

        return mapper.toDto(sensorRepo.save(existente));
    }

    public void delete(Integer id) {
        sensorRepo.delete(buscarOuFalhar(id));
    }

    private SensorRfid buscarOuFalhar(Integer id) {
        return sensorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor RFID id " + id + " não encontrado"));
    }
}
