package br.monitoramento.motu.rabbit.consumer;

import br.monitoramento.motu.rabbit.events.LocalizacaoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LocalizacaoConsumer {

    private static final Logger log = LoggerFactory.getLogger(LocalizacaoConsumer.class);

    @RabbitListener(queues = "#{@queueLocalizacoes.name}")
    public void onMessage(LocalizacaoEvent event) {
        log.info("[Rabbit] mensagem na fila LOCALIZACOES -> {}", event);
    }
}
