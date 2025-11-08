package br.monitoramento.motu.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_MOTOS = "mototrack.motos";

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    @Bean
    public NewTopic motosTopic() {
        return TopicBuilder.name(TOPIC_MOTOS)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
