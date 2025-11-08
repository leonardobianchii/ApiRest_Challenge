package br.monitoramento.motu.mapper;

import br.monitoramento.motu.dto.LocalizacaoMotoRfidDto;
import br.monitoramento.motu.model.LocalizacaoMotoRfid;
import br.monitoramento.motu.model.Moto;
import br.monitoramento.motu.model.SensorRfid;

import org.springframework.stereotype.Component;



@Component
public class LocalizacaoMotoRfidMapper {

    public LocalizacaoMotoRfid toEntity(LocalizacaoMotoRfidDto dto, Moto moto, SensorRfid sensor) {
        LocalizacaoMotoRfid l = new LocalizacaoMotoRfid();
        l.setId(dto.id());
        l.setMoto(moto);
        l.setSensor(sensor);
        l.setData(dto.data());
        return l;
    }

    public LocalizacaoMotoRfidDto toDto(LocalizacaoMotoRfid l) {
        Integer idMoto = l.getMoto() != null ? l.getMoto().getId() : null;
        Integer idSensor = l.getSensor() != null ? l.getSensor().getId() : null;
        return new LocalizacaoMotoRfidDto(
                l.getId(),
                idMoto,
                idSensor,
                l.getData()
        );
    }
}
