Путь к серверу в Postman - localhost:8080/library

Для работы приложения требуется Docker. Сначала запускаем Docker, после открываем проект в IDE. Переходим в терминал и прописываем "docker-compose up". После запускаем метод Main.

Получить книги - GET /books

Получить авторов - GET /authors

Удалить книгу - DELETE {"name":"Frankenstein"}

Удалить автора (так же удаляются все его книги) - DELETE {"name":"Mary Shelley"}

Добавить книгу (необходим существующий автор) - POST [{"name":"Frankenstein"},{"author":"Mary Shelley"}]

Добавить автора - POST {"name":"Mary Shelley"}

Изменить автора - PUT [{"name":"Mary Shelley"},{"newName":"Mary"}]

Изменить книгу - PUT [{"name":"Frankenstein"},{"newName":"The Modern Prometheus"}]
