package br.monitoramento.motu.rabbit.events;

import java.time.LocalDateTime;

public record LocalizacaoEvent(
        Integer id,
        Integer idMoto,
        Integer idSensor,
        LocalDateTime data,
        String type // "created" | "updated" | "deleted"
) {}
