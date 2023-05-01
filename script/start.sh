#!/usr/bin/env bash

cd /home/ubuntu/api
pid=`ps -ef | grep "api" | grep -v 'grep' | awk '{print $2}'`
if [ $pid ]
 then
   kill $pid
fi

if [[ "$DEPLOYMENT_GROUP_NAME" == "stg" ]]
then
    echo "Dev-Deploy"
    apihome='/home/ubuntu/ground-api/ground/ground-application/build/libs'
    SPRING_PROFILES_ACTIVE=staging nohup java -jar -Duser.timezone="Asia/Seoul" -Dspring.config.location=/home/ubuntu/ground.yml ${apihome}/yourssu-ground-application.jar 1>> /home/ubuntu/log/ground.log 2>> /home/ubuntu/log/ground-error.log &
elif [[ "$DEPLOYMENT_GROUP_NAME" == "ground-prd" ]]
then
    echo "Prd-Deploy"
    apihome='/home/ubuntu/api/api/ground-application/build/libs'
    SPRING_PROFILES_ACTIVE=production nohup java -jar -Duser.timezone="Asia/Seoul" -Dspring.profiles.active=dev ${apihome}/api.jar
fi