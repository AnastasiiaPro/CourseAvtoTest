[![CourseAvtoTest](https://github.com/AnastasiiaPro/CourseAvtoTest/actions/workflows/gradle.yml/badge.svg)](https://github.com/AnastasiiaPro/CourseAvtoTest/actions/workflows/gradle.yml)

# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»

## Документация
1. [План автоматизации](https://github.com/AnastasiiaPro/CourseAvtoTest/blob/main/report/Plan.md)
2. [Отчёт о проведенном тестировании](https://github.com/AnastasiiaPro/CourseAvtoTest/blob/main/report/Report.md)
3. [Отчет о проведённой автоматизации тестирования](https://github.com/AnastasiiaPro/CourseAvtoTest/blob/main/report/Summary.md)

## **Инструкция для запуска автотестов**
1. Клонировать проект:`https://github.com/AnastasiiaPro/CourseAvtoTest`
2. Открыть проект в IntelliJ IDEA
3. Запустить Docker Desktop

### Подключение SUT к MySQL
1. В терминале 1 в корне проекта запустить контейнеры: `docker-compose up -d`
2. В терминале 2 запустить приложение: `java -jar ./artifacts/aqa-shop.jar -D db.url`
3. Проверить, что приложение успешно запустилось (ввести URL в браузере Сhrome: `http://localhost/8080`)
4. В терминале 3 запустить тесты: `./gradlew clean test`
5. Создать отчёт Allure и открыть в браузере `.\gradlew allureserve`
6. Закрыть отчёт в терминаме 3:   `CTRL + C --> y --> Enter`
7. Остановить приложение в терминале 2: `CTRL + C`
8. Остановить контейнеры в терминале 1:`docker-compose down`

### Подключение SUT к PostgreSQL
1. В терминале 1 в корне проекта запустить контейнеры: `docker-compose up -d`
2. В терминале 2 запустить приложение: `java -jar ./artifacts/aqa-shop.jar -D db.pgurl`
3. Проверить, что приложение успешно запустилось (ввести URL в браузере Сhrome: `http://localhost/8080`)
4. В терминале 3 запустить тесты: `.\gradlew clean test`
5. Создать отчёт Allure и открыть в браузере `.\gradlew allureserve`
6. Закрыть отчёт в терминале 3:   `CTRL + C --> y --> Enter`
7. Остановить приложение в терминале 2: `CTRL + C`
8. Остановить контейнеры в терминале 1:`docker-compose down`
