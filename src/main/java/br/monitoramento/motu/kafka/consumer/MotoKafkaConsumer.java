package br.monitoramento.motu.kafka.consumer;

import br.monitoramento.motu.rabbit.events.MotoEvent;
import br.monitoramento.motu.kafka.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MotoKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(MotoKafkaConsumer.class);

    @KafkaListener(topics = KafkaConfig.TOPIC_MOTOS, groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(@Payload MotoEvent event) {
        log.info("[Kafka] mensagem no topico MOTOS -> {}", event);
    }
}
