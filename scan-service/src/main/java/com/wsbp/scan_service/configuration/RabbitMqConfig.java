package com.wsbp.scan_service.configuration;

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

    public static final String QUEUE_NAME = "scanQueue";
    public static final String EXCHANGE_NAME = "scanExchange";
    public static final String ROUTING_KEY = "scanRoutingKey";

    @Bean
    public Queue scanQueue(){
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue scanQueue, TopicExchange exchange) {
        return BindingBuilder.bind(scanQueue).to(exchange).with(ROUTING_KEY);
    }

    //initialize Jackson converter to conver objects to strings and the other way
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Second Queue
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
}
