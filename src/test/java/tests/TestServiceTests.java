package tests;

import io.qameta.allure.Link;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.Addition;
import pojo.Message;
import pojo.Response;
import util.BaseRequests;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс тестирования функциональности точек доступа сервиса http://localhost:8080
 * Создание сущности: POST /api/create
 * Удаление сущности: DELETE /api/delete/{id}
 * Получение сущности: GET /api/get/{id}
 * Получение всех сущностей: GET /api/getAll
 * Обновление сущности: PATCH /api/patch/{id}
 * <p>Тесты используют аннотации JUnit 5 для настройки и выполнения тестов.</p>
 */

public class TestServiceTests {
    /**
     * Id по которому проходит тестирование и очистка сервиса
     */
    private String userId;
    private RequestSpecification requestSpecification;

    /**
     * Метод, выполняющийся перед каждым тестом. Конфигурирует запрос.
     */
    @BeforeEach
    public void setUp() throws Exception {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    /**
     * Тестиурет создание pojo.Message
     */
    @Test
    @Description("Данный тест проверяет создание pojo.Message")
    @Link(name = "POST", url = "/api/create")
    public void createMessageTest() {
        Message messageForCreate = Message.builder().build();
        userId = given().spec(requestSpecification).body(messageForCreate).when().post("/api/create").then().statusCode(200).extract().body().asString();
        Response responseMessageById = given().spec(requestSpecification).when().get("/api/get/" + userId).then().statusCode(200).extract().as(Response.class, ObjectMapperType.GSON);

        assertTrue(BaseRequests.comparingEntities(userId, messageForCreate, responseMessageById));
    }

    /**
     * Тестиурет получение pojo.Message по id
     */
    @Test
    @Description("Данный тест проверяет получение pojo.Message по id")
    @Link(name = "GET", url = "/api/get/{id}")
    public void getMessageByIdTest() {
        Message messageForCreate = Message.builder().build();
        userId = given().spec(requestSpecification).body(messageForCreate).when().post("/api/create").then().statusCode(200).extract().body().asString();
        Response responseMessageById = given().when().get("/api/get/" + userId).then().statusCode(200).extract().as(Response.class, ObjectMapperType.GSON);

        assertTrue(BaseRequests.comparingEntities(userId, messageForCreate, responseMessageById));
    }

    /**
     * Тестиурет получение всех pojo.Message
     */
    @Test
    @Description("Данный тест проверяет получение всех pojo.Message")
    @Link(name = "GET", url = "/api/all")
    public void getAllMessageTest() {
        Message messageForCreate = Message.builder().build();
        userId = given().spec(requestSpecification).body(messageForCreate).when().post("/api/create").then().statusCode(200).extract().body().asString();
        given().when().get("/api/getAll").then().statusCode(200);
    }

    /**
     * Тестиурет обновление pojo.Message
     */
    @Test
    @Description("Данный тест проверяет обновление pojo.Message по id")
    @Link(name = "PATCH", url = "/api/patch/{id}")
    public void patchMessageByIdTest() {
        Message messageForCreate = Message.builder().build();
        userId = given().spec(requestSpecification).body(messageForCreate).when().post("/api/create").then().statusCode(200).extract().body().asString();
        Message messagePatch = Message.builder().title("Сведения раз").important_numbers(List.of(01, 02, 03)).verified(true).addition(Addition.builder().additional_info("Дополнительные сведения раз").additional_number(234).build()).build();
        given().spec(requestSpecification).body(messagePatch).when().patch("/api/patch/" + userId).then().statusCode(204);

        Response responseMessageById = given().when().get("/api/get/" + userId).then().statusCode(200).extract().as(Response.class, ObjectMapperType.GSON);

        assertTrue(BaseRequests.comparingEntities(userId, messagePatch, responseMessageById));
    }

    /**
     * Тестиурет удаление pojo.Message
     */
    @Test
    @Description("Данный тест проверяет удаление pojo.Message по id")
    @Link(name = "DELETE", url = "/api/delete/{id}")
    public void deleteMessageByIdTest() {
        Message messageForCreate = Message.builder().build();
        userId = given().spec(requestSpecification).body(messageForCreate).when().post("/api/create").then().statusCode(200).extract().body().asString();
        given().when().delete("/api/delete/" + userId).then().statusCode(204);
        given().when().get("/api/get/" + userId).then().statusCode(500);
        userId = given().spec(requestSpecification).body(messageForCreate).when().post("/api/create").then().statusCode(200).extract().body().asString();
    }

    /**
     * Очищает сервис после выполнения каждого теста.
     */
    @AfterEach
    public void tearDown() throws Exception {
        BaseRequests.deleteMessageById(userId);
    }
}
