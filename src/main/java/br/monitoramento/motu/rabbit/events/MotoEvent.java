package br.monitoramento.motu.rabbit.events;

public record MotoEvent(
        Integer id,
        String placa,
        Integer idModelo,
        Long idFilial,
        String status,
        Integer kmRodado,
        String type // ex: "created" | "updated" | "deleted"
) {}
