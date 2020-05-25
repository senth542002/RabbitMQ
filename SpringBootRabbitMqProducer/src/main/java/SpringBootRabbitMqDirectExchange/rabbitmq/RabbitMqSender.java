package SpringBootRabbitMqDirectExchange.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import SpringBootRabbitMqDirectExchange.model.Employee;

@Service
public class RabbitMqSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.routingkey}")
	private String routingKey;

	public void send(Employee employee) {
		rabbitTemplate.convertAndSend(exchange, routingKey, employee);
		System.out.println("Send message="+ employee);
	}

}
