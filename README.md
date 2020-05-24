# RabbitMQ

#Start a Rabbit MQ docker instance by enabling the Management and server ports outside. /n

 docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management

 #RabbitMQ UI \n
 http://localhost:15672/#/queues

 #Docker to find RabbitMQ status <br>
 docker logs <name of instance>

 #Docker to list live process <br>
 docker ps

 #Docker to list all processes including stopped ones <br>
 docker ps -a

 #docker to stop a container
 docker kill <process id>

 #docker remove the container
 docker rm <process id>

 #Log into a existing docker container
 docker exec -it -d <container_id> bash