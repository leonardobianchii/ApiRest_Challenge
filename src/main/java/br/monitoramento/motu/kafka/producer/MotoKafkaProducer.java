package br.monitoramento.motu.kafka.producer;

import br.monitoramento.motu.rabbit.events.MotoEvent;
import br.monitoramento.motu.kafka.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MotoKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(MotoKafkaProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MotoKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MotoEvent event) {
        kafkaTemplate.send(KafkaConfig.TOPIC_MOTOS, event.id() == null ? "moto" : event.id().toString(), event)
                .whenComplete((res, ex) -> {
                    if (ex != null) {
                        log.error("[Kafka] erro ao publicar evento de moto", ex);
                    } else {
                        log.info("[Kafka] publicado em {} part={} offset={} -> {}",
                                res.getRecordMetadata().topic(),
                                res.getRecordMetadata().partition(),
                                res.getRecordMetadata().offset(),
                                event);
                    }
                });
    }
}
