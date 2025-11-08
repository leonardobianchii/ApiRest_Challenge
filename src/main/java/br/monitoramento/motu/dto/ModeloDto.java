package br.monitoramento.motu.dto;

import jakarta.validation.constraints.*;

public record ModeloDto(
        Integer id,
        @NotBlank @Size(max = 100) String nome
) { }
