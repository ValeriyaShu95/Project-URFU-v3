# Используем официальный образ OpenJDK для Java
FROM openjdk:11-jre-slim

# Устанавливаем директорию приложения в контейнере
WORKDIR /app

# Копируем собранный JAR файл приложения в контейнер
COPY target/project.jar /app/project.jar

# Команда для запуска приложения при запуске контейнера
CMD ["java", "-jar", "project.jar"]