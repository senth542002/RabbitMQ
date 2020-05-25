package SpringBootRabbitMqConsumer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import SpringBootRabbitMqConsumer.model.Employee;

@Component
public class RabbitMqConsumer {
	
	@RabbitListener(queues = "${rabbitmq.queue}")
	public void receivedMessage(Employee employee) {
		System.out.println("Received Message From RabbitMq: "+employee);
	}

}
