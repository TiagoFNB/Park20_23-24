package park20.Park_Microservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    // Define queues and exchanges for each microservice
    @Bean
    public Queue customerQueue() {
        return new Queue("customer-queue", false);
    }

    @Bean
    public Queue managerQueue() {
        return new Queue("manager-queue", false);
    }

    @Bean
    public Queue authQueue() {
        return new Queue("auth-queue", false);
    }

    @Bean
    public Queue rabbitPostRegisterQueue() {
        return new Queue("rabbit-postregister-queue", false);
    }

    @Bean
    public Queue rabbitCheckParkQueue() {
        return new Queue("rabbit-check-park-queue", false);
    }

    @Bean
    public Queue rabbitOpenSlotQueue() {
        return new Queue("rabbit-open-slot-queue", false);
    }

    @Bean
    public Queue rabbitParkByIdQueue() {
        return new Queue("park-byId-queue", false);
    }

    @Bean
    public Queue rabbitParksManagerQueue() {
        return new Queue("parks-manager-queue", false);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}