package br.monitoramento.motu.rabbit.producer;

import br.monitoramento.motu.rabbit.events.MotoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MotoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbit.exchange}")     private String exchange;
    // vamos usar chaves específicas dentro do padrão topic
    private static final String ROUTING_CREATED = "moto.created";
    private static final String ROUTING_UPDATED = "moto.updated";
    private static final String ROUTING_DELETED = "moto.deleted";

    public MotoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCreated(MotoEvent event) {
        rabbitTemplate.convertAndSend(exchange, ROUTING_CREATED, event);
    }

    public void sendUpdated(MotoEvent event) {
        rabbitTemplate.convertAndSend(exchange, ROUTING_UPDATED, event);
    }

    public void sendDeleted(MotoEvent event) {
        rabbitTemplate.convertAndSend(exchange, ROUTING_DELETED, event);
    }
}
