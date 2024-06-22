#!/bin/bash

# 환경 변수 설정
PROJECT_DIR="/home/ec2-user/small-talker-backend"
JAR_NAME="backend-0.1.jar"
LOG_FILE="/var/log/small-talker/backend.log"

# 배포 로그 출력
echo "Deploying Spring Boot application..."

# 프로젝트 디렉토리로 이동
cd $PROJECT_DIR

# 최신 코드 가져오기
echo "Pulling latest code from Git..."
CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)
git pull origin $CURRENT_BRANCH

# Gradle 빌드
echo "Building the project with Gradle..."
chmod +x ./gradlew
./gradlew build

# 실행 중인 애플리케이션 PID 찾기
echo "Finding existing application PID..."
JAVA_PID=$(pgrep -f "java -jar $PROJECT_DIR/build/libs/$JAR_NAME")

echo "Find JAVA_PID: $JAVA_PID"

# 기존 애플리케이션 종료
if [ -n "$JAVA_PID" ]; then
    echo "Stopping existing application with JAVA_PID: $JAVA_PID"
    kill -9 $JAVA_PID
else
    echo "No existing application running."
fi

# 새 애플리케이션 실행
echo "Starting new application..."

case $1 in 
    "prod")
        nohup java -jar -Dspring.profiles.active=prod $PROJECT_DIR/build/libs/$JAR_NAME > $LOG_FILE 2>&1 &
        ;;
    "dev")
        nohup java -jar -Dspring.profiles.active=dev $PROJECT_DIR/build/libs/$JAR_NAME > $LOG_FILE 2>&1 &
        ;;
    *)
        nohup java -jar -Dspring.profiles.active=dev $PROJECT_DIR/build/libs/$JAR_NAME > $LOG_FILE 2>&1 &
        ;;
esac

echo "Deployment completed."
