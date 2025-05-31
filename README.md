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