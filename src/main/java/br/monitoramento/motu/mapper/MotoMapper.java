package br.monitoramento.motu.mapper;

import org.springframework.stereotype.Component;
import br.monitoramento.motu.dto.MotoDto;
import br.monitoramento.motu.model.FilialDepartamento;
import br.monitoramento.motu.model.Moto;
import br.monitoramento.motu.model.Modelo;


@Component
public class MotoMapper {

    public Moto toEntity(MotoDto dto, Modelo modelo, FilialDepartamento filial) {
        Moto moto = new Moto();
        moto.setId(dto.id());
        moto.setModelo(modelo);
        moto.setFilial(filial);
        moto.setPlaca(dto.placa());
        moto.setStatus(dto.status());
        moto.setKmRodado(dto.kmRodado());
        return moto;
    }

    public MotoDto toDto(Moto moto) {
        return new MotoDto(
                moto.getId(),
                moto.getModelo().getId(),
                moto.getFilial().getId(),
                moto.getPlaca(),
                moto.getStatus(),
                moto.getKmRodado()
        );
    }
}

