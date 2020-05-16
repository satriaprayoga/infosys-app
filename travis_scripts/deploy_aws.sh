echo "Launching $BUILD_NAME IN AMAZON ECS"
ecs-cli configure --cluster infosysApp --default-launch-type EC2 --config-name infosysApp --region ap-southeast-1
ecs-cli configure profile --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY --profile-name infosysApp-profile
ecs-cli compose --file docker/docker-compose.yml up --cluster-config infosysApp --ecs-profile infosysApp-profile
rm -rf ~/.ecs