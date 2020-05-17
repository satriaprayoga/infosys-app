#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the eureka server to start on port $(getPort $EUREKASERVER_PORT)"
echo "********************************************************"
while ! `nc -z infosys-eureka  $(getPort $EUREKASERVER_PORT)`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Starting Search Server with Configuration Service via Eureka :  $EUREKASERVER_URI:$SERVER_PORT"
echo "********************************************************"

echo "********************************************************"
echo "Starting RABBITMQ Server with :  $RABBITMQ_URI:$RABBITMQ_PORT"
echo "********************************************************"

java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.rabbitmq.host=$RABBITMQ_URI               \
     -Dspring.rabbitmq.port=$RABBITMQ_PORT              \
     -jar /usr/local/searchserver/@project.build.finalName@.jar