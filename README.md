// Запуск FrontEnd в Docker
docker run -p 3000:3000 --rm ghcr.io/dmitry-bizin/front-react-avito:v1.21

// Используемый порт
server.port=8888

// Информация для подключения БД
spring.datasource.url=jdbc:postgresql://localhost:5432/shop
spring.datasource.username=student
spring.datasource.password=chocolatefrog
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true