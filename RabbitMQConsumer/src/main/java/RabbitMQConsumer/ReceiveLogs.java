package RabbitMQConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogs {

	private final static String EXCHANGE_NAME = "logs";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		String queueName = channel.queueDeclare().getQueue();
		
		channel.queueBind(queueName, EXCHANGE_NAME, "");
		
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
		
		boolean autoAck = true;
		
		channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> {});
		
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
