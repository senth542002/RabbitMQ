package SpringBootRabbitMqExchanges.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqTopicConfig {
	
	@Bean
	Queue marketingQueue() {
		return new Queue("marketingQueue", false);
	}
	
	@Bean
	Queue financeQueue() {
		return new Queue("financeQueue", false);
	}
	
	@Bean
	Queue adminQueue() {
		return new Queue("adminQueue", false);
	}

	@Bean
	Queue allQueue() {
		return new Queue("allQueue", false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("topic-exchange");
	}
	
	@Bean
	Binding marketingBinding(Queue marketingQueue, TopicExchange exchange) {
		return BindingBuilder.bind(marketingQueue).to(exchange).with("queue.marketing");
	}
	
	@Bean
	Binding financeBinding(Queue financeQueue, TopicExchange exchange) {
		return BindingBuilder.bind(financeQueue).to(exchange).with("queue.finance");
	}
	
	@Bean
	Binding adminBinding(Queue adminQueue, TopicExchange exchange) {
		return BindingBuilder.bind(adminQueue).to(exchange).with("queue.admin");
	}
	
	@Bean
	Binding allBinding(Queue allQueue, TopicExchange exchange) {
		return BindingBuilder.bind(allQueue).to(exchange).with("queue.*");
	}


}
