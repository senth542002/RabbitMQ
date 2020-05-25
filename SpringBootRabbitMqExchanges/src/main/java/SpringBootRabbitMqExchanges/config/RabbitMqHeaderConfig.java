package SpringBootRabbitMqExchanges.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqHeaderConfig {
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
	HeadersExchange exchange() {
		return new HeadersExchange("header-exchange");
	}
	
	@Bean
	Binding marketingBinding(Queue marketingQueue, HeadersExchange exchange) {
		return BindingBuilder.bind(marketingQueue).to(exchange).where("department").matches("marketing");
	}
	
	@Bean
	Binding financeBinding(Queue financeQueue, HeadersExchange exchange) {
		return BindingBuilder.bind(financeQueue).to(exchange).where("department").matches("finance");
	}
	
	@Bean
	Binding adminBinding(Queue adminQueue, HeadersExchange exchange) {
		return BindingBuilder.bind(adminQueue).to(exchange).where("department").matches("admin");
	}
	
}
