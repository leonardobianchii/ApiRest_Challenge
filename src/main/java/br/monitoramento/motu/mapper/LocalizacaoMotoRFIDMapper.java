package br.monitoramento.motu.mapper;

import br.monitoramento.motu.dto.LocalizacaoMotoRFIDDTO;
import br.monitoramento.motu.model.LocalizacaoMotoRFID;
import org.springframework.stereotype.Component;

@Component
public class LocalizacaoMotoRFIDMapper {

    public LocalizacaoMotoRFIDDTO toDTO(LocalizacaoMotoRFID localizacao) {
        LocalizacaoMotoRFIDDTO dto = new LocalizacaoMotoRFIDDTO();
        dto.setIdMoto(localizacao.getMoto().getIdMoto());
        dto.setIdSensor(localizacao.getSensorRFID().getIdSensor());
        dto.setNrX(localizacao.getNrX());
        dto.setNrY(localizacao.getNrY());
        dto.setDtLocalizacao(localizacao.getDtLocalizacao());
        return dto;
    }

    public LocalizacaoMotoRFID toEntity(LocalizacaoMotoRFIDDTO dto) {
        LocalizacaoMotoRFID localizacao = new LocalizacaoMotoRFID();
        localizacao.setNrX(dto.getNrX());
        localizacao.setNrY(dto.getNrY());
        localizacao.setDtLocalizacao(dto.getDtLocalizacao());
        return localizacao;
    }
}

