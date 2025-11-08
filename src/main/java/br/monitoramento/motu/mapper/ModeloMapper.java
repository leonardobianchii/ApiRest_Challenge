package br.monitoramento.motu.mapper;

import org.springframework.stereotype.Component;

import br.monitoramento.motu.dto.ModeloDto;
import br.monitoramento.motu.model.Modelo;

@Component
public class ModeloMapper {

    public Modelo toEntity(ModeloDto dto) {
        Modelo m = new Modelo();
        m.setId(dto.id());
        m.setNome(dto.nome());
        return m;
    }

    public ModeloDto toDto(Modelo m) {
        return new ModeloDto(m.getId(), m.getNome());
    }
}
