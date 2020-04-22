#!/bin/sh
echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z infosys-eureka  $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Starting Zuul Service with $CONFIGSERVER_URI"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     -Dspring.profiles.active=$PROFILE                          \
     -jar /usr/local/gateway/@project.build.finalName@.jar