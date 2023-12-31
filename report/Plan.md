# **План автоматизации тестирования**

### **Исходные данные:**

|     Поле      | Валидные данные                                                 | Невалидные данные                      |
|:-------------:|:----------------------------------------------------------------|:---------------------------------------|
| `Номер карты` | APPROVED карта - `1111 2222 3333 4444`                          | DECLINED карта - `5555 6666 7777 8888` |
|               |                                                                 | любой случайный номер                  |
|               |                                                                 | пустое поле                            |
|               |                                                                 | все нули                               |
|               |                                                                 | 1 цифра                                |
|               |                                                                 | 15 цифр                                |
|    `Месяц`    | числовое значение из 2-х цифр (от 01 до 12),                    | пустое поле                            |
|               |                                                                 | все нули                               |
|               |                                                                 | одна цифра                             |                                                                  | 13                  |
|               |                                                                 | 13 цифр                                |
|     `Год`     | числовое значение из 2-х цифр                                   | пустое поле                            |
|               | не может быть годом в прошлом                                   | все нули                               |
|               | срок, на который выдана карта - 5 лет                           | текущий год +6                         |
|               |                                                                 | текущий год -1                         |
|               |                                                                 | одна цифра                             |
|  `Владелец`   | фамилия и имя на латинице,                                      | пустое поле                            |
|               | состоящие из двух слов через пробел                             | длина - 21 буквенный символ            |
|               | имя и фамилия клиента не должны превышать 20 знаков с пробелами | спец.символы                           |
|               | верхний регистр                                                 | цифры                                  |
|               |                                                                 | ФИ кириллицей                          |
|               |                                                                 | нижний регистр                         |
|               |                                                                 | 1 слово                                |
|     `CVC`     | любое число,                                                    | пустое поле                            |
|               | состоящее из 3-х цифр (от 100 до 999)                           | все нули                               |
|               |                                                                 | одна цифра                             |
|               |                                                                 | две цифры                              |

## **Перечень автоматизируемых сценариев**

### **UI сценарии**

#### **Предусловия:**
1. Открыть страницу http://localhost:8080
2. Нажать кнопку `Купить`, открывается `Оплата по карте` ИЛИ
3. Нажать кнопку `Купить в кредит`, открывается `Кредит по данным карты`

|  №  | Отправка формы                                                                               | Результат                            |
|:---:|:---------------------------------------------------------------------------------------------|:-------------------------------------|
|     | **Позитивные сценарии**                                                                      |                                      |
|  1  | Покупка `Оплата по карте` / `Кредит по данным карты` со статусом `APPROVED` валидные данные  | `Успешно` Операция одобрена банком   |
|  2  | Покупка `Оплата по карте` / `Кредит по данным карты` со статусом `DECLINED` валидные данные  | `Ошибка` Отказ в проведении операции |
|     | **Негативны сценарии**                                                                       |                                      |
|  3  | Поле`Номер карты` - ввод невалидных данных `Оплата по карте` / `Кредит по данным карты`      | `Ошибка` Отказ в проведении операции |
|  4  | Поле `Месяц` - ввод невалидных данных `Оплата по карте` / `Кредит по данным карты`           | `Ошибка` Отказ в проведении операции |
|  5  | Поле `Год` - ввод невалидных данных `Оплата по карте` / `Кредит по данным карты`             | `Ошибка` Отказ в проведении операции |
|  6  | Поле `Владелец` - ввод невалидных данных `Оплата по карте` / `Кредит по данным карты`        | `Ошибка` Отказ в проведении операции |
|  7  | Поле `CVC` - ввод невалидных данных `Оплата по карте` / `Кредит по данным карты`             | `Ошибка` Отказ в проведении операции |
|  8  | Отправление данных с незаполненными полями `Оплата по карте` / `Кредит по данным карты`      | `Ошибка` Отказ в проведении операции |

### **API сценарии**

|  №  | Отправка формы                                                                         | Результат                           |
|:---:|:---------------------------------------------------------------------------------------|:------------------------------------|
|  1  | Отправка POST запроса `Купить` с валидным body и карты со статусом `APPROVED`          | статус 200, появление  записи в БД  |
|  2  | Отправка POST запроса `Купить в кредит` с валидным body и карты со статусом `APPROVED` | статус 200, появление  записи в БД  |
|  3  | Отправка POST запроса `Купить` с валидным body и карты со статусом `DECLINED`          | статус 200, появление  записи в БД  |
|  4  | Отправка POST запроса `Купить в кредит` с валидным body и карты со статусом `DECLINED` | статус 200, появление  записи в БД  |
|  5  | Отправка POST запроса с пустым значением у атрибута `body`                             | статус 400, отсутствие записи в БД  |
|  6  | Отправка POST запроса с пустым значением у атрибута `number`                           | статус 400, отсутствие записи в БД  |
|  7  | Отправка POST запроса с пустым значением у атрибута `month`                            | статус 400, отсутствие записи в БД  |
|  8  | Отправка POST запроса с пустым значением у атрибута `year`                             | статус 400, отсутствие записи в БД  |
|  9  | Отправка POST запроса с пустым значением у атрибута `holder`                           | статус 400, отсутствие записи в БД  |
| 10  | Отправка POST запроса с пустым значением у атрибута `cvc`                              | статус 400, отсутствие записи в БД  |

## **Перечень используемых инструментов с обоснованием выбора**

|  №  | Используемый инструмент                             | Обоснование выбора                                                                                                                       |
|:---:|:----------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------|
|  1  | Среда разработки `IntelliJ IDEA`                    | Удобная среда для Java с поддержкой современных технологий и фреймворков                                                                 |
|  2  | Язык программирования: `Java JDK11`                 | Имеет набор готового ПО для разработки и запуска приложений                                                                              |
|  3  | Система автоматической сборки: `Gradle`             | В Gradle удобно подключать зависимости, рациональный объёма кода, и довольно гибкая система по сравнению с Maven.                        |
|  4  | Тестовая среда `JUnit5`                             | Тестовый фреймворк, совместимый с JVM, содержит необходимые аннотации и assert’ы                                                         |
|  5  | Для UI тестирования: `Selenide`                     | Библиотека для написания лаконичных и стабильных UI тестов с открытым исходным кодом                                                     |
|  6  | Для API тестирования: `Rest Assured`                | Библиотека для тестирования REST API, позволяет автоматизировать тестирование get и post запросов                                        |
|  7  | Плагин `Lombok`                                     | Для минимизации строк кода за счет аннотаций позволяет оптимизировать код автотестов                                                     |
|  8  | Библиотека `JavaFaker`                              | Для генерации случайных тестовых данных                                                                                                  |
|  9  | Система репортинга: `Allure`                        | Для создания отчетов о тестировании и наглядного отображения прохождения тестов и ошибок                                                 |
| 10  | Платформа контейнеризации `Docker Desktop`          | Для развертывания контейнеров с базами данных MySQL и сервисов банка                                                                     |
| 11  | Система контроля версий `Git` и веб-сервис `GitHub` | Git - возможность одновременной параллельной разработки, есть интеграция с IntelliJ IDEA. GitHub - удобный интерфейс, интегрирован с Git |

## **Перечень и описание возможных рисков при автоматизации**

1. Отсутствие документации на приложение (какое поведение системы считать ошибочным)
2. Тесты могут не покрывать всех возможных негативных сценариев 
3. Падение автотестов UI даже при незначительном изменении кода (изменения: структуры сайта, локаторов элементов) 
5. Потеря актуальности тестов API при изменение структуры запросов со стороны банка
6. Некорректность тестовых данных при работе с Faker
7. Актуализация, обновление и поддрежание тестов может занять достаточно большое количество времени

## **Интервальная оценка с учётом рисков (в часах)**

|  №  | Наименование работы                                          | Оценка с учетом рисков ~30% (в часах) |
|:---:|:-------------------------------------------------------------|:-------------------------------------:|
|  1  | Ознакомление с проектом + план автоматизации тестирования    |                  12                   |
|  2  | Написание и отладка авто-тестов                              |                  28                   |
|  3  | Настройка окружения и поведение тестирования                 |                   4                   |
|  4  | Подготовка и оформление баг-репортов                         |                   8                   |
|  5  | Формирование отчета о проведенном тестировании               |                   8                   |
|  6  | Формирование отчета о проведённой автоматизации тестирования |                   8                   |
|     | Итого                                                        |                  68                   |

## **План сдачи работ**

|  №  | Наименование работы                                 | Дата сдачи |
|:---:|:----------------------------------------------------|:----------:|
|  1  | План автоматизации тестирования                     | 21.12.2023 |
|  2  | Написание и отладка авто-тестов                     | 23.12.2023 |
|  3  | Подготовка и оформление репортов                    | 25.12.2023 |
|  4  | Отчеты по тестированию и автоматизации тестирования | 28.12.2023 |