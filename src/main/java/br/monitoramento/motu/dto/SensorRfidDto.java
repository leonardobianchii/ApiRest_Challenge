package br.monitoramento.motu.dto;

import jakarta.validation.constraints.*;

public record SensorRfidDto(
        Integer id,
        @NotBlank @Size(max = 100) String nome,
        @NotNull Long idFilial,
        @Size(max = 100) String localizacao
) { }

