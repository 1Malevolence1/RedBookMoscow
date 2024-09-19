# Проект "Красная книга" - Микросервисы для ухода за окружающей средой

Данный проект направлен на управление данными Красной книги и мониторинг экологического состояния. Мы создали систему микросервисов на базе Spring Boot, которая обрабатывает данные о редких и исчезающих видах, а также включает парсер для извлечения информации из предоставленного PDF-файла.

## Описание

RedBookMoscow — это веб-приложение для добавления, редактирования и отслеживания редких видов растений и животных Красной книги Москвы. Оно помогает собирать и систематизировать данные для специалистов, способствуя защите природы и планированию благоустройства города, учитывая охрану краснокнижных видов.

Технические особенности:
Используются HTML, CSS и JavaScript для фронтенда, Spring Boot и Hibernate для бэкенда. Поддерживается импорт данных о краснокнижных видах из PDF, а также редактирование информации специалистами.

Уникальность:
Удобное решение, которое позволяет специалистам редактировать данные и импортировать информацию из PDF в базу данных.
## Установка

1. Склонируйте репозиторий:
    ```bash
        git clone https://github.com/1Malevolence1/RedBookMoscow
    ```
2.Перейдите в директорию проекта:
    ```bash
        cd RedBookMoscow
    ```

3. Соберите и запустите каждый микросервис:
    ```bash
        ...
    ```
   Docker-Compose вылетал с багами, поэтому на разработке.

## Использование
Запустив docker-compose, перейдите на http://localhost:8085/api/admin/main в своем браузере.

### Парсинг PDF файлов
Находиться в `parsing-service` главный файл `parse_pdf.py`.
Еще на этапе доработки, но уже можно скормить ему pdf и получить текст и фото.
Необходимо добавить взаимодействие с другими сервисами.