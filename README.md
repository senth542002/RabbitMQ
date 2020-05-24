# RabbitMQ

#Start a Rabbit MQ docker instance by enabling the Management and server ports outside.
 docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management

 #RabbitMQ UI
 http://localhost:15672/#/queues

 #Docker to find RabbitMQ status
 docker logs <name of instance>

 #Docker to list live process
 docker ps

 #Docker to list all processes including stopped ones
 docker ps -a

 #docker to stop a container
 docker kill <process id>

 #docker remove the container
 docker rm <process id>