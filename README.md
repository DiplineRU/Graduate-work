Описание проекта
Проект представляет собой backend-сервис, написанный на Java с использованием Spring Boot в IntelliJ IDEA, обеспечивающий поддержку фронтенда интернет-магазина по продаже товаров. Включает управление объявлениями, комментариями, пользователями и загрузку изображений.

Основные возможности
CRUD операции для объявлений и комментариев
Регистрация и аутентификация пользователей
Загрузка и хранение изображений
REST API для интеграции с фронтендом
Технологии
Spring Boot
Spring Data JPA
Hibernate
Java 17+
Liquibase
PostgreSQL
Maven/Gradle
Docker
REST API
Требования
JDK 11+
Maven 3.6+
PostgreSQL версии 12+
IntelliJ IDEA или другая IDE для Java
Docker 20.10+ (опционально, для контейнеризации)

// Запуск FrontEnd в Docker
docker run -p 80:80 --rm ghcr.io/dmitry-bizin/front-react-avito:1.22-server-port-8888

// Запуск FrontEnd в браузере
localhost:80/

// Используемый порт
server.port=8888

// Информация для подключения БД
spring.datasource.url=jdbc:postgresql://localhost:5432/shop
spring.datasource.username=student
spring.datasource.password=chocolatefrog
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Разработчики
3 команда 32 поток

Гулян Артем
Слепухин Игорь
Шишкин Сергей