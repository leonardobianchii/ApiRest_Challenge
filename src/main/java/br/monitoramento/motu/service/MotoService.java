package br.monitoramento.motu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.monitoramento.motu.dto.MotoDto;
import br.monitoramento.motu.exception.ResourceNotFoundException;
import br.monitoramento.motu.mapper.MotoMapper;
import br.monitoramento.motu.model.FilialDepartamento;
import br.monitoramento.motu.model.Modelo;
import br.monitoramento.motu.model.Moto;
import br.monitoramento.motu.rabbit.events.MotoEvent;
import br.monitoramento.motu.rabbit.producer.MotoProducer;
import br.monitoramento.motu.repository.FilialDepartamentoRepository;
import br.monitoramento.motu.repository.ModeloRepository;
import br.monitoramento.motu.repository.MotoRepository;

import br.monitoramento.motu.kafka.producer.MotoKafkaProducer;

@Service
public class MotoService {

    @Autowired private MotoRepository motoRepository;
    @Autowired private ModeloRepository modeloRepository;
    @Autowired private FilialDepartamentoRepository filialRepository;
    @Autowired private MotoMapper mapper;

    // rabbit: ja estava no projeto
    @Autowired private MotoProducer motoProducer;

    // kafka: adicionando agora
    @Autowired private MotoKafkaProducer motoKafkaProducer;

    public List<MotoDto> findAll() {
        return motoRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public MotoDto findById(Integer id) {
        return mapper.toDto(buscarOuFalhar(id));
    }

    public MotoDto create(MotoDto dto) {
        // valida o basico
        Modelo modelo = modeloRepository.findById(dto.idModelo())
                .orElseThrow(() -> new ResourceNotFoundException("Modelo nao encontrado"));
        FilialDepartamento filial = filialRepository.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento nao encontrado"));

        // salva
        Moto salvo = motoRepository.save(mapper.toEntity(dto, modelo, filial));
        MotoDto saida = mapper.toDto(salvo);

        // monta evento uma vez e publica nos dois
        MotoEvent evt = toEvent(salvo, "created");
        motoProducer.sendCreated(evt);     // rabbit
        motoKafkaProducer.send(evt);       // kafka

        return saida;
    }

    public MotoDto update(Integer id, MotoDto dto) {
        Moto existente = buscarOuFalhar(id);

        // carrega relacionamentos
        Modelo modelo = modeloRepository.findById(dto.idModelo())
                .orElseThrow(() -> new ResourceNotFoundException("Modelo nao encontrado"));
        FilialDepartamento filial = filialRepository.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento nao encontrado"));

        // aplica mudancas
        existente.setModelo(modelo);
        existente.setFilial(filial);
        existente.setPlaca(dto.placa());
        existente.setStatus(dto.status());
        existente.setKmRodado(dto.kmRodado());

        // persiste
        Moto atualizado = motoRepository.save(existente);
        MotoDto saida = mapper.toDto(atualizado);

        // publica nos dois canais
        MotoEvent evt = toEvent(atualizado, "updated");
        motoProducer.sendUpdated(evt);     // rabbit
        motoKafkaProducer.send(evt);       // kafka

        return saida;
    }

    public void delete(Integer id) {
        Moto existente = buscarOuFalhar(id);

        // remove do banco
        motoRepository.delete(existente);

        // evento de exclusao para auditoria/consumo externo
        MotoEvent evt = toEvent(existente, "deleted");
        motoProducer.sendDeleted(evt);     // rabbit
        motoKafkaProducer.send(evt);       // kafka
    }

    private Moto buscarOuFalhar(Integer id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto id " + id + " nao encontrada"));
    }

    // helper: transforma entidade em evento
    private MotoEvent toEvent(Moto m, String type) {
        Integer idModelo = (m.getModelo() != null ? m.getModelo().getId() : null);
        Long idFilial    = (m.getFilial()  != null ? m.getFilial().getId()  : null); // seu Filial usa Long

        return new MotoEvent(
                m.getId(),
                m.getPlaca(),
                idModelo,
                idFilial,
                m.getStatus(),
                m.getKmRodado(),
                type
        );
    }
}
