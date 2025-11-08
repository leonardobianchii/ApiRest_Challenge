package br.monitoramento.motu.rabbit.events;

public record SensorEvent(
        Integer id,
        String nome,
        Integer idFilial,
        Integer idLocalizacao, // se existir relação
        String type
) {}
