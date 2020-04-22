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
echo "Starting Authentication Service                           "
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -jar /usr/local/authserver/@project.build.finalName@.jar