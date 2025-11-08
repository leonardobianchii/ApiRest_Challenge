package br.monitoramento.motu.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.rabbit.exchange}")           private String exchangeName;
    @Value("${app.rabbit.queue.motos}")        private String motosQueue;
    @Value("${app.rabbit.queue.localizacoes}") private String locsQueue;
    @Value("${app.rabbit.queue.sensores}")     private String sensoresQueue;

    @Value("${app.rabbit.routing.motos}")        private String motosRouting;
    @Value("${app.rabbit.routing.localizacoes}") private String locsRouting;
    @Value("${app.rabbit.routing.sensores}")     private String sensoresRouting;

    // exchange topic
    @Bean
    public TopicExchange mototrackExchange() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    // filas
    @Bean
    public Queue queueMotos()        { return QueueBuilder.durable(motosQueue).build(); }
    @Bean
    public Queue queueLocalizacoes() { return QueueBuilder.durable(locsQueue).build(); }
    @Bean
    public Queue queueSensores()     { return QueueBuilder.durable(sensoresQueue).build(); }

    // bindings
    @Bean
    public Binding bindMotos(Queue queueMotos, TopicExchange mototrackExchange) {
        return BindingBuilder.bind(queueMotos).to(mototrackExchange).with(motosRouting);
    }
    @Bean
    public Binding bindLocs(Queue queueLocalizacoes, TopicExchange mototrackExchange) {
        return BindingBuilder.bind(queueLocalizacoes).to(mototrackExchange).with(locsRouting);
    }
    @Bean
    public Binding bindSensores(Queue queueSensores, TopicExchange mototrackExchange) {
        return BindingBuilder.bind(queueSensores).to(mototrackExchange).with(sensoresRouting);
    }

    // converter para JSON (Jackson)
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
