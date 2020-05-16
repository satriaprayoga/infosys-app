echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push infosys/infosys-eureka:$BUILD_NAME
docker push infosys/infosys-gateway:$BUILD_NAME
docker push infosys/infosys-oauth:$BUILD_NAME
docker push infosys/infosys-message:$BUILD_NAME
docker push infosys/infosys-customer:$BUILD_NAME
docker push infosys/infosys-destination:$BUILD_NAME
docker push infosys/infosys-search:$BUILD_NAME
docker push infosys/infosys-webclient:$BUILD_NAME