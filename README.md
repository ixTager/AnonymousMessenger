# Простой мессенджер для общения
## 📖 О проекте
### Простой мессенджер для анонимного общения. Работает по протоколу STOMP.


## 🛠 Стек технологий
- ### Sping Framework
- ### RabbitMQ
- ### Redis
- ### H2 Database(разработка) / PostgreSQL(продакшн)

## ️⚙️Установка
`git clone https://github.com/ixTager/AnonymousMessenger`

## 🚀Запуск
### Перед запуском проекта измените файл `application.yaml` под ваши конфигурации сервера. Пример стандартного `application.yaml`:
```
server:
    port: 8080

spring:
  application:
    name: demo
  rabbitmq:
    username: guest
    password: guest
    host: localhost
  data:
    redis:
      port: 6379
      host: localhost
      timeout: 2000ms
  datasource:
    url: jdbc:h2:mem:test
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2-console
```

### После того, как вы это сделали запустите файл `AnonymousMessengerApplication.java`.

