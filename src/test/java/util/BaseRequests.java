package util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.Message;
import pojo.Response;

import java.io.IOException;
import java.util.Objects;

import static io.restassured.RestAssured.given;
/**
 * Утилитный класс для работы с базовыми HTTP-запросами к REST API.
 * <p>
 * Данный класс предоставляет методы для инициализации спецификации запросов,
 * удаления сущностей по ID и сравнения сущностей.
 */
public class BaseRequests {
    static ParametersProvider parametersProvider = new ParametersProvider();
    /**
     * Подготовка спецификации запроса.
     * <p>
     * Данный метод создает и настраивает спецификацию HTTP-запроса,
     * устанавливая тип контента, базовый URI и ожидаемый тип ответа.
     *
     * @return спецификация запроса {@link RequestSpecification}
     * @throws IOException если возникла ошибка при получении параметров API
     */
    public static RequestSpecification initRequestSpecification() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(parametersProvider.getApi("api.url"))
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }
    /**
     * Удаление сущности по ID.
     * <p>
     * Данный метод отправляет DELETE-запрос к API для удаления сообщения
     * по указанному ID.
     *
     * @param messageId уникальный идентификатор сообщения для удаления
     */
    public static void deleteMessageById(String messageId) {
        given()
                .when()
                .delete("/api/delete/" + messageId)
                .then()
                .statusCode(204);
    }
    /**
     * Сравнение сущностей.
     * <p>
     * Данный метод сравнивает идентификатор сообщения, его заголовок и
     * список чисел между ожидаемой сущностью и сущностью, извлеченной
     * из ответа API.
     *
     * @param idMessage уникальный идентификатор сообщения
     * @param message ожидалось {@link Message}
     * @param response полученный ответ от API {@link Response}
     * @return true, если все поля совпадают, иначе false
     */
    public static boolean comparingEntities(String idMessage, Message message, Response response) {
        if (!Objects.equals(idMessage, response.getId()) || !Objects.equals(message.getTitle(), response.getTitle()) ||
                !Objects.equals(message.getImportantNumbers(), response.getImportantNumbers())) {
            return false;
        } else {
            return true;
        }
    }


}
