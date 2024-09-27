# Тест-кейсы для проверки функциональности:

## Сервиса "test-service"

## Тест-кейс 1: Создание Message (POST /api/create)

### Цель:
Создание Message.

### Предусловия:
1. Запустить сервис "test-service"
2. API -  http://localhost:8080/api/create.
3. Значения полей сущности Message такие как addition, important_numbers, title, verified указаны по умолчанию.

### Шаги:
1. Отправить POST-запрос на http://localhost:8080/api/create с телом запроса, содержащим следующие данные:
   {
   "addition": {
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [ 42, 87, 15 ],
   "title": "Заголовок сущности",
   "verified": true
   }
2. Получить Response Body с числом (id).

### Ожидаемый результат:
Статус-код : 200
Body с числом (id).

### Проверка:
1. Отправить GET-запрос на http://localhost:8080//api/get/{полученныйid}.
2. Получить Response Body с JSON:
   {
   "id": {полученныйid},
   "title": "Заголовок сущности",
   "verified": true,
   "addition": {
   "id": {полученныйid},
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [
   42,
   87,
   15
   ]
   }.
3. Сравнить JSON из POST запроса с JSON из Response.

---

## Тест-кейс 2: Получение Message по id (GET /api/get/{d})

### Цель:
Получить Message по id.

### Предусловия:
1. Запустить сервис "test-service"
2. API -  http://localhost:8080/api/get/{d}.
3. Значения полей сущности Message такие как addition, important_numbers, title, verified указаны по умолчанию.

### Шаги:
1. Отправить POST-запрос на http://localhost:8080/api/create с телом запроса, содержащим следующие данные:
   {
   "addition": {
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [ 42, 87, 15 ],
   "title": "Заголовок сущности",
   "verified": true
   }
2. Получить Body с числом (id).
3. Отправить GET-запрос на http://localhost:8080//api/get/{полученныйid}.
4. Получить Body с JSON:
   {
   "id": {полученныйid},
   "title": "Заголовок сущности",
   "verified": true,
   "addition": {
   "id": {полученныйid},
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [
   42,
   87,
   15
   ]
   }.

### Ожидаемый результат:
Статус-код : 200
Response Body с JSON.

### Проверка:
1. Сравнить JSON из POST запроса с JSON из Response.

---
## Тест-кейс 3: Получение всех Message (GET /api/getALL)

### Цель:
Получить все Message.

### Предусловия:
1. Запустить сервис "test-service"
2. API -  http://localhost:8080/api/getAll.
3. Значения полей сущности Message такие как addition, important_numbers, title, verified указаны по умолчанию.

### Шаги:
1. Отправить POST-запрос на http://localhost:8080/api/create с телом запроса, содержащим следующие данные:
   {
   "addition": {
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [ 42, 87, 15 ],
   "title": "Заголовок сущности",
   "verified": true
   }
2. Получить Body с числом (id).
3. Отправить GET-запрос на http://localhost:8080//api/getAll.
4. Получить Body с JSON:
   {
   "id": {полученныйid},
   "title": "Заголовок сущности",
   "verified": true,
   "addition": {
   "id": {полученныйid},
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [
   42,
   87,
   15
   ]
   }.

### Ожидаемый результат:
Статус-код : 200

---
## Тест-кейс 4: Обновление Message по id (PATCH /api/patch/{id})

### Цель:
Обновить Message по id.

### Предусловия:
1. Запустить сервис "test-service"
2. API -  http://localhost:8080/api/patch/{id}.
3. Значения полей сущности Message такие как addition, important_numbers, title, verified указаны по умолчанию.

### Шаги:
1. Отправить POST-запрос на http://localhost:8080/api/patch/{id} с телом запроса, содержащим следующие данные:
   {
   "addition": {
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [ 42, 87, 15 ],
   "title": "Заголовок сущности",
   "verified": true
   }
2. Получить Body с числом (id).
3. Отправить PATCH-запрос на http://localhost:8080/api/patch/{полученныйid} с телом запроса, содержащим следующие данные:
   {
   "addition": {
   "additional_info": "Дополнительные сведения раз",
   "additional_number": 234
   },
   "important_numbers": [ 01, 02, 03 ],
   "title": "Сведения раз",
   "verified": true
   }
4. Получить статус-код : 204.

### Ожидаемый результат:
Статус-код : 200

### Проверка:
1. Отправить GET-запрос на http://localhost:8080//api/get/{полученныйid}
2. Получить Body с JSON:
   {
   "id": {полученныйid},
   "title": "Сведения раз",
   "verified": true,
   "addition": {
   "id": {полученныйid},
   "additional_info": "Дополнительные сведения раз",
   "additional_number": 123
   },
   "important_numbers": [ 01, 02, 03 ],
   }.
3. Сравнить JSON из POST запроса с JSON из Response.

---
## Тест-кейс 5: Удаление Message по id (DELETE /api/delete/{id})

### Цель:
Удалить Message по id.

### Предусловия:
1. Запустить сервис "test-service"
2. API -  http://localhost:8080/api/delete/{id}.
3. Значения полей сущности Message такие как addition, important_numbers, title, verified указаны по умолчанию.

### Шаги:
1. Отправить POST-запрос на http://localhost:8080/api/create с телом запроса, содержащим следующие данные:
   {
   "addition": {
   "additional_info": "Дополнительные сведения",
   "additional_number": 123
   },
   "important_numbers": [ 42, 87, 15 ],
   "title": "Заголовок сущности",
   "verified": true
   }
2. Получить Body с числом (id).
3. Отправить DElETE-запрос на http://localhost:8080/api/delete/{полученныйid}
4. Получить статус-код : 204.

### Ожидаемый результат:
Статус-код : 204

### Проверка:
1. Отправить GET-запрос на http://localhost:8080//api/get/{полученныйid}
2. Получить статус-код: 500
---