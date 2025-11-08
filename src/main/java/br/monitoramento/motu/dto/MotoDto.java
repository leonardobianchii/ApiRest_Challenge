package br.monitoramento.motu.dto;

import jakarta.validation.constraints.*;

public record MotoDto(
        Integer id,
        @NotNull Integer idModelo,
        @NotNull Long idFilial,
        @NotBlank @Size(max = 10) String placa,
        @Size(max = 30) String status,
        @PositiveOrZero Integer kmRodado
) {}