# Простой мессенджер для общения
## 📖 О проекте
### Простой мессенджер для анонимного общения. Работает по протоколу STOMP и реализуется через Docker.

## 🛠 Стек технологий
- ### Sping Framework
- ### RabbitMQ
- ### Redis
- ### H2 Database(разработка) / PostgreSQL(продакшн)
- ### Docker

## ️⚙️Установка
`git clone https://github.com/ixTager/AnonymousMessenger`

## 🚀Запуск
### Перед запуском проекта измените файл `application.yaml` под ваши конфигурации сервера. Пример стандартного `application.yaml`:
```
spring:
  application:
    name: AnonymousMessenger
  rabbitmq:
    username: guest
    password: guest
    host: localhost
    port: 5672
  h2:
    console:
      path: /h2-console
      enabled: true
  data:
    redis:
      host: localhost
      port: 6379
      timeout: 200ms
  datasource:
    url: jdbc:h2:mem:messages
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop

server:
  port: 8080

```

### После вашей настройки `application.yaml` установите следующие образы (Images) в Docker:
- ### redis:latest
- ### rabbitmq:management
- ### buildo/h2database:latest

### Затем, после вашей установки в терминале введите следующие команды:
```
docker run --hostname=3def739d02d4 --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=REDIS_VERSION=8.8.0 --network=bridge --workdir=/data -p 6379:6379 --restart=no --runtime=runc -d redis:latest
```
```
docker run --hostname=9b580bc4b0f6 --env=PATH=/opt/rabbitmq/sbin:/opt/erlang/bin:/opt/openssl/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=ERLANG_INSTALL_PATH_PREFIX=/opt/erlang --env=OPENSSL_INSTALL_PATH_PREFIX=/opt/openssl --env=RABBITMQ_DATA_DIR=/var/lib/rabbitmq --env=RABBITMQ_VERSION=4.3.1 --env=RABBITMQ_PGP_KEY_ID=0x0A9AF2115F4687BD29803A206B73A36E6026DFCA --env=RABBITMQ_HOME=/opt/rabbitmq --env=HOME=/var/lib/rabbitmq --env=LANG=C.UTF-8 --env=LANGUAGE=C.UTF-8 --env=LC_ALL=C.UTF-8 --env=RUNNING_UNDER_SYSTEMD=true --volume=/var/lib/rabbitmq --network=bridge -p 15672:15672 -p 5672:5672 -p 61613:61613 --restart=no --label='org.opencontainers.image.version=24.04' --runtime=runc -d rabbitmq:management
```
```
docker run --hostname=7a9bb32837af --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=LANG=C.UTF-8 --env=JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64 --env=JAVA_VERSION=7u111 --env=JAVA_DEBIAN_VERSION=7u111-2.6.7-2~deb8u1 --env=RELEASE_DATE=2017-04-23 --env=H2DATA=/h2-data --volume=/h2-data --network=bridge -p 8082:8082 -p 9092:9092 --restart=no --runtime=runc -d buildo/h2database:latest
```
### После запуска этих команд запустите файл `AnonymousMessengerApplication.java` для запуска нашего приложения.
