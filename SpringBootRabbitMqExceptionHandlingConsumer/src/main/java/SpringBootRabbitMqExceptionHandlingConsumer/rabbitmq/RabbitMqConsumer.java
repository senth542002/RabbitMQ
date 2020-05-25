package SpringBootRabbitMqExceptionHandlingConsumer.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import SpringBootRabbitMqExceptionHandlingConsumer.exception.InvalidSalaryException;
import SpringBootRabbitMqExceptionHandlingConsumer.model.Employee;


@Service
public class RabbitMqConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);

	@RabbitListener(queues = "rabbitMqMessage.queue")
	public void recievedMessage(Employee employee) throws InvalidSalaryException {
		logger.info("Recieved Message From RabbitMQ: " + employee);
		if (employee.getSalary() < 0) {
			throw new InvalidSalaryException();
		}
	}

}
