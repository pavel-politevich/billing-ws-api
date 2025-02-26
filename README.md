# Billing_WS_API

### Сборка и деплой сервиса

Jenkins: http://srv-jenkins-02:8080/view/BWSAPI/

### Запуск и логи

/usr/bin/java - путь к java

/opt/bwsapi/logs - логи

/opt/bwsapi/billing-ws-api.jar - путь к jar

/opt/bwsapi/application.properties - путь к файлу конфига

сейчас сервис запускается такой командой:

/usr/bin/java -jar /opt/bwsapi/billing-ws-api.jar --spring.config.location=file:/opt/bwsapi/application.properties --spring.profiles.active=prod

Рестарт: docker restart bwsapi-bwsapi-1

### Actuator

Actuator: http://server-name:8080/actuator

HealthCheck: http://server-name:8080/actuator/health

Метрики Prometheus: http://server-name:8080/actuator/prometheus

### Swagger

http://server-name:8080/swagger-ui/index.html

### Администрирование

http://srv-bwsapi-t01/   (Тест)

Таблицы в схеме bwsapi БД deepdb.

Таблица|Описание
---|---
USERS|пользователь и пароль
AUTHORITIES|сопоставление пользователя и роли
ROLES_GROUP|список ролей
SERVICE_ROLE_ACCESS_RIGHTS|сопоставление роли и сервиса
SERVICES|сервисы с url-путями. Заполняется разработчиком.
HTTP_METHODS|справочник http-методов. Заполняется разработчиком.


Генерация хэша для пароля 123456:

 `curl --location 'http://server-name:8080/api/v1/gen_pass?pass=123456'`