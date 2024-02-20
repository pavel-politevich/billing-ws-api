# Billing_WS_API

/usr/bin/java - путь к java

/opt/bwsapi/logs - логи

/opt/bwsapi/billing-ws-api.jar - путь к jar

/opt/bwsapi/application.properties - путь к файлу конфига

сейчас сервис запускается такой командой:

/usr/bin/java -jar /opt/bwsapi/billing-ws-api.jar --spring.config.location=file:/opt/bwsapi/application.properties

рестарт службы:

sudo systemctl restart bwsapi.service

Actuator: http://server-name:8080/actuator

Метрики для Prometheus: http://server-name:8080/actuator/prometheus

HealthCheck: http://server-name:8080/api/v1/test