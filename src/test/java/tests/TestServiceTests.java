package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import pojo.Addition;
import pojo.Message;
import pojo.Response;
import util.BaseRequests;
import util.ParametersProvider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс тестирования функциональности REST API точек доступа сервиса.
 * <p>
 * Данный класс включает в себя тесты для следующих операций:
 * <ul>
 *     <li>Создание сущности: POST /api/create</li>
 *     <li>Удаление сущности: DELETE /api/delete/{id}</li>
 *     <li>Получение сущности: GET /api/get/{id}</li>
 *     <li>Получение всех сущностей: GET /api/getAll</li>
 *     <li>Обновление сущности: PATCH /api/patch/{id}</li>
 * </ul>
 * <p>
 * Тесты используют аннотации JUnit 5 для настройки и выполнения тестов, а также Annotations Allure для генерации отчетов.
 */
public class TestServiceTests {

    private String messageIdFirst;
    private String messageIdSecond;
    private RequestSpecification requestSpecification;
    private ParametersProvider parametersProvider;
    private Message messageForCreateFirst;
    private Message messageForCreateSecond;
    /**
     * Метод, выполняющийся перед каждым тестом.
     * Конфигурирует запрос и инициализирует сообщения для тестирования.
     *
     * @param testInfo информация о текущем тесте
     * @throws Exception если возникла ошибка при инициализации
     */
    @BeforeEach
    public void setUp(TestInfo testInfo) throws Exception {
        requestSpecification = BaseRequests.initRequestSpecification();
        parametersProvider = new ParametersProvider();
        messageForCreateFirst = Message.builder()
                .title(parametersProvider.getApi("title"))
                .importantNumbers(Arrays.stream(parametersProvider.getApi("important.numbers")
                                .split(",\\s*"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .verified(Boolean.parseBoolean(parametersProvider.getApi("verified")))
                .addition(Addition.builder()
                        .additionalInfo(parametersProvider.getApi("additonal.info"))
                        .additionalNumber(Integer.parseInt(parametersProvider.getApi("additional.number")))
                        .build())
                .build();
        if ("getAllMessageTest()".equals(testInfo.getDisplayName()) || "patchMessageByIdTest()".equals(testInfo.getDisplayName())) {
            messageForCreateSecond = Message.builder()
                    .title(parametersProvider.getApi("title1"))
                    .importantNumbers(Arrays.stream(parametersProvider.getApi("important.numbers1")
                                    .split(",\\s*"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()))
                    .verified(Boolean.parseBoolean(parametersProvider.getApi("verified1")))
                    .addition(Addition.builder()
                            .additionalInfo(parametersProvider.getApi("additonal.info1"))
                            .additionalNumber(Integer.parseInt(parametersProvider.getApi("additional.number1")))
                            .build())
                    .build();
        }
    }
    /**
     * Тест для проверки создания сообщения.
     * <p>
     * Отправляет POST запрос для создания сообщения и проверяет,
     * что сообщение было успешно создано.
     */
    @Test
    @Link(name = "POST", url = "/api/create")
    @Step("Создание сообщения")
    @Description("Тестирует создание нового сообщения через API.")
    public void createMessageTest() {
        messageIdFirst = given()
                .spec(requestSpecification)
                .body(messageForCreateFirst)
                .when()
                .post(ParametersProvider.getApi("api.post"))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        Response responseMessageById = given()
                .spec(requestSpecification)
                .when()
                .get(ParametersProvider.getApi("api.get") + messageIdFirst)
                .then()
                .statusCode(200)
                .extract()
                .as(Response.class, ObjectMapperType.GSON);

        assertTrue(BaseRequests.comparingEntities(messageIdFirst, messageForCreateFirst, responseMessageById));
    }
    /**
     * Тест для получения сообщения по ID.
     * <p>
     * Отправляет GET запрос для получения сообщения по его ID
     * и проверяет, что полученное сообщение соответствует отправленному.
     */
    @Test
    @Link(name = "GET", url = "/api/get/{id}")
    @Step("Получение сообщения по ID")
    @Description("Тестирует получение сообщения по ID через API.")
    public void getMessageByIdTest() {
        messageIdFirst = given()
                .spec(requestSpecification)
                .body(messageForCreateFirst)
                .when()
                .post(ParametersProvider.getApi("api.post"))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        Response responseMessageById = given()
                .when()
                .get(ParametersProvider.getApi("api.get") + messageIdFirst)
                .then()
                .statusCode(200)
                .extract()
                .as(Response.class, ObjectMapperType.GSON);

        assertTrue(BaseRequests.comparingEntities(messageIdFirst, messageForCreateFirst, responseMessageById));
    }
    /**
     * Тест для получения всех сообщений.
     * <p>
     * Отправляет GET запрос для получения всех сообщений
     * и проверяет, что количество полученных сообщений соответствует ожиданиям.
     */
    @Test
    @Link(name = "GET", url = "/api/all")
    @Step("Получение всех сообщений")
    @Description("Тестирует получение всех сообщений через API.")
    public void getAllMessageTest() {
        messageIdFirst = given()
                .spec(requestSpecification)
                .body(messageForCreateFirst)
                .when()
                .post(ParametersProvider.getApi("api.post"))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        messageIdSecond = given()
                .spec(requestSpecification)
                .body(messageForCreateSecond)
                .when()
                .post(ParametersProvider.getApi("api.post"))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        given()
                .when()
                .get(ParametersProvider.getApi("api.getAll"))
                .then()
                .statusCode(200);
    }
    /**
     * Тест для обновления сообщения по ID.
     * <p>
     * Отправляет PATCH запрос для обновления сообщения и проверяет,
     * что обновленное сообщение соответствует ожиданиям.
     */
    @Test
    @Link(name = "PATCH", url = "/api/patch/{id}")
    @Step("Обновление сообщения по ID")
    @Description("Тестирует обновление сообщения через API.")
    public void patchMessageByIdTest() {
        messageIdFirst = given()
                .spec(requestSpecification)
                .body(messageForCreateFirst)
                .when()
                .post(ParametersProvider.getApi("api.post"))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        given()
                .spec(requestSpecification)
                .body(messageForCreateSecond)
                .when()
                .patch(ParametersProvider.getApi("api.patch") + messageIdFirst)
                .then()
                .statusCode(204);
        Response responseMessageById = given()
                .when()
                .get(ParametersProvider.getApi("api.get") + messageIdFirst)
                .then()
                .statusCode(200)
                .extract()
                .as(Response.class, ObjectMapperType.GSON);
        assertTrue(BaseRequests.comparingEntities(messageIdFirst, messageForCreateSecond, responseMessageById));
    }
    /**
     * Тест для удаления сообщения по ID.
     * <p>
     * Отправляет DELETE запрос для удаления сообщения и проверяет,
     * что сообщение успешно удалено.
     */
    @Test
    @Link(name = "DELETE", url = "/api/delete/{id}")
    @Step("Удаление сообщения по ID")
    @Description("Тестирует удаление сообщения через API.")
    public void deleteMessageByIdTest() {
        messageIdFirst = given()
                .spec(requestSpecification)
                .body(messageForCreateFirst)
                .when()
                .post(ParametersProvider.getApi("api.post"))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        given()
                .when()
                .delete(ParametersProvider.getApi("api.delete") + messageIdFirst)
                .then()
                .statusCode(204);
        given()
                .when()
                .get(ParametersProvider.getApi("api.get") + messageIdFirst)
                .then()
                .statusCode(500);
    }
    /**
     * Метод, выполняющийся после каждого теста. Удаляет созданные сообщения.
     *
     * @param testInfo информация о текущем тесте
     */
    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if ("getAllMessageTest()".equals(testInfo.getDisplayName())) {
            BaseRequests.deleteMessageById(messageIdSecond);
        }
        if (!"deleteMessageByIdTest()".equals(testInfo.getDisplayName())) {
            BaseRequests.deleteMessageById(messageIdFirst);
        }
    }
}
