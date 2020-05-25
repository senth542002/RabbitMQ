# RabbitMQ

#Links

https://rabbitmq.com/tutorials/tutorial-one-java.html <br>
https://javainuse.com/messaging/rabbitmq/exchange

#Start a Rabbit MQ docker instance by enabling the Management and server ports outside. <br>
 docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management

 #RabbitMQ UI <br>
 http://localhost:15672/#/queues

 #Docker to find RabbitMQ status <br>
 docker logs name_of_container

 #Docker to list live process <br>
 docker ps

 #Docker to list all processes including stopped ones <br>
 docker ps -a

 #docker to stop a container <br>
 docker kill container_id

 #docker remove the container <br>
 docker rm container_id

 #Log into a existing docker container <br>
 docker exec -it -d container_id bash  echo "Hello from Container"