package br.monitoramento.motu.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public record LocalizacaoMotoRfidDto(
        Integer id,
        @NotNull Integer idMoto,
        @NotNull Integer idSensor,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime data
) { }
