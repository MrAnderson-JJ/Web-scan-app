package com.scan_app.output_service.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_REPORT = "reportQueue";
    public static final String EXCHANGE_REPORT = "reportExchange";
    public static final String ROUTING_REPORT = "reportRoutingKey";

    @Bean
    public Queue reportQueue(){
        return new Queue(QUEUE_REPORT, false);
    }

    @Bean
    public TopicExchange reportExchange() {
        return new TopicExchange(EXCHANGE_REPORT);
    }

    @Bean
    public Binding reportBinding(Queue reportQueue, TopicExchange reportExchange) {
        return BindingBuilder.bind(reportQueue).to(reportExchange).with(ROUTING_REPORT);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
