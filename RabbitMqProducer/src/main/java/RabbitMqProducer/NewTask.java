package RabbitMqProducer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	
	private final static String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		try(Connection connection = factory.newConnection();
			Channel channel = connection.createChannel()) {
			
			channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
			
			String message = String.join(" ", args);
			
			channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());	
				System.out.println(" [x] Sent '"+ message +"'");
		}


	}

}
