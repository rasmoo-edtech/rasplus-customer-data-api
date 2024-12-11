package com.client.rasplus.api.customer.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AMQPConfig {

    private static final String RECOVERY_CODE_EMAIL = "recovery.code.email";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public Queue createQueue() {
        return QueueBuilder.nonDurable(RECOVERY_CODE_EMAIL).build();
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

}
