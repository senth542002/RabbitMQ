package RabbitMQConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Worker {
	private final static String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
		
		System.out.println(" [*] Waiting for meddages. To exit press CTRL+C");
		
		channel.basicQos(1);
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '"+ message + "' in First Consumer");
			
			try {
				doWork(message);
			} finally {
				System.out.println(" [x] Done");
			}
		};
		
		boolean autoAck = false;
		
		channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {});
	}

	private static void doWork(String task) {
		for(char ch: task.toCharArray()) {
			if(ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}


}
