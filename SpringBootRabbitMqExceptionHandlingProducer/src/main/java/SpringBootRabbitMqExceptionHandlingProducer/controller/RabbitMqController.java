package SpringBootRabbitMqExceptionHandlingProducer.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringBootRabbitMqExceptionHandlingProducer.model.Employee;

@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMqController {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId,@RequestParam("salary") int salary) {
		Employee emp=new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		emp.setSalary(salary);

		amqpTemplate.convertAndSend("rabbitMqMessageExchange", "rabbitMqMessage", emp);
		return "Message sent to the RabbitMQ Successfully";
	}

}
