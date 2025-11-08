package br.monitoramento.motu.mapper;

import org.springframework.stereotype.Component;

import br.monitoramento.motu.dto.SensorRfidDto;
import br.monitoramento.motu.model.FilialDepartamento;
import br.monitoramento.motu.model.SensorRfid;

@Component
public class SensorRfidMapper {

    public SensorRfid toEntity(SensorRfidDto dto, FilialDepartamento filial) {
        SensorRfid s = new SensorRfid();
        s.setId(dto.id());
        s.setNome(dto.nome());
        s.setFilial(filial);
        s.setLocalizacao(dto.localizacao());
        return s;
    }

    public SensorRfidDto toDto(SensorRfid s) {
        Long idFilial = s.getFilial() != null ? s.getFilial().getId() : null;
        return new SensorRfidDto(
                s.getId(),
                s.getNome(),
                idFilial,
                s.getLocalizacao()
        );
    }
}
