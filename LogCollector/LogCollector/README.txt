Для настройки БД скачайте H2 базу с этой ссылки
http://www.h2database.com/html/download.html

Для создания таблиц выполните следующие скрипты
--таблица для хранения логов пользователя
create table MESSAGE_LOG (
    created_at datetime,
    first_name varchar2(256),
    message varchar2(4000),
    second_name varchar2(256),
    user_id long
);
--создание индекса для более быстрого поиска по дате
CREATE INDEX CREATED_AT_INDX ON MESSAGE_LOG (created_at);
--создание пользователя
create user if not exists scott password 'tiger' admin;

Для запуска БД из папки h2/bin выполните команду
java -cp h2*.jar org.h2.tools.Server -ifNotExists

Запуск приложения осуществляется из класса src/main/java/com/runner/Runner.java