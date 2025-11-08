package br.monitoramento.motu.mapper;

import br.monitoramento.motu.dto.FilialDepartamentoDto;
import br.monitoramento.motu.model.FilialDepartamento;

import org.springframework.stereotype.Component;

@Component
public class FilialDepartamentoMapper {

    public FilialDepartamento toEntity(FilialDepartamentoDto dto) {
        FilialDepartamento f = new FilialDepartamento();
        f.setId(dto.id());
        f.setNome(dto.nome());
        return f;
    }

    public FilialDepartamentoDto toDto(FilialDepartamento f) {
        return new FilialDepartamentoDto(
                f.getId(),
                f.getNome()
        );
    }
}
