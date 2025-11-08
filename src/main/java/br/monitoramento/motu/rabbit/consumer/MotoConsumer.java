package br.monitoramento.motu.rabbit.consumer;

import br.monitoramento.motu.rabbit.events.MotoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MotoConsumer {

    private static final Logger log = LoggerFactory.getLogger(MotoConsumer.class);

    @Value("${app.rabbit.queue.motos}")
    private String queueName;

    // injetando o nome da fila pelo property no annotation
    @RabbitListener(queues = "#{@queueMotos.name}")
    public void onMessage(MotoEvent event) {
        log.info("[Rabbit] mensagem na fila MOTOS -> {}", event);
    }
}
