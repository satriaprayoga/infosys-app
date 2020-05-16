echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push satriaprayoga/infosys-eureka:$BUILD_NAME
docker push satriaprayoga/infosys-gateway:$BUILD_NAME
docker push satriaprayoga/infosys-oauth:$BUILD_NAME
docker push satriaprayoga/infosys-message:$BUILD_NAME
docker push satriaprayoga/infosys-customer:$BUILD_NAME
docker push satriaprayoga/infosys-destination:$BUILD_NAME
docker push satriaprayoga/infosys-search:$BUILD_NAME
docker push satriaprayoga/infosys-webclient:$BUILD_NAME