package SpringBootRabbitMqDirectExchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringBootRabbitMqDirectExchange.model.Employee;
import SpringBootRabbitMqDirectExchange.rabbitmq.RabbitMqSender;

@RestController
@RequestMapping(value = "/rabbitMq")
public class RabbitMqController {
	
	@Autowired
	RabbitMqSender rabbitMqSender;
	
	
	@GetMapping(value = "/producer")
	public String producer(@RequestParam String empName, @RequestParam String empId) {
		Employee employee = new Employee(empName, empId);
		rabbitMqSender.send(employee);
		
		return "Message sent to the RabbitMQ Successfully.";
	}
	
}
