package br.monitoramento.motu.rabbit.producer;

import br.monitoramento.motu.rabbit.events.LocalizacaoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalizacaoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbit.exchange}") private String exchange;

    public LocalizacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCreated(LocalizacaoEvent event) {
        rabbitTemplate.convertAndSend(exchange, "localizacao.created", event);
    }

    public void sendUpdated(LocalizacaoEvent event) {
        rabbitTemplate.convertAndSend(exchange, "localizacao.updated", event);
    }

    public void sendDeleted(LocalizacaoEvent event) {
        rabbitTemplate.convertAndSend(exchange, "localizacao.deleted", event);
    }
}
