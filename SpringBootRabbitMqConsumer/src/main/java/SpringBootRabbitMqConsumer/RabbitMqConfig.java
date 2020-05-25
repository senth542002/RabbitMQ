package SpringBootRabbitMqConsumer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import SpringBootRabbitMqConsumer.rabbitmq.RabbitMqListner;

@Configuration
public class RabbitMqConfig {
	
	@Value("${rabbitmq.queue}")
	private String queueName;
	
	@Value("${spring.rabbitmq.username}")
	private String userName;
	
	@Value("${spring.rabbitmq.password}")
	private String password;
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


	
	//MessageListnerContainer using default ConnectionFactory
	@Bean
	MessageListenerContainer messageListnerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMqListner());
		
		return simpleMessageListenerContainer;
	}
	
/*	//Creating Custom Connection Factory
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setUsername(userName);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}
	
	//MessageListnerContainer using Custom Conenction factory
	@Bean
	MessageListenerContainer messageListnerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMqListner());
		return simpleMessageListenerContainer;
	} */
}
