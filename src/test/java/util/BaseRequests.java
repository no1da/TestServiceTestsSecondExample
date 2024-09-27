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

public class BaseRequests {
    static ParametersProvider parametersProvider = new ParametersProvider();

    /**
     * Подготовка спецификации запросаю
     *
     * @return спецификация
     * @throws IOException
     */
    public static RequestSpecification initRequestSpecification() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(parametersProvider.getAppUrl())
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }

    /**
     * Удаление по id
     *
     * @param messageId
     */
    public static void deleteMessageById(String messageId) {
        given()
                .when()
                .delete("/api/delete/" + messageId)
                .then()
                .statusCode(204);
    }

    /**
     * Удаление по id
     *
     * @param idMessage, message, pojo.Response response
     * @return boolean
     */
    public static boolean comparingEntities(String idMessage, Message message, Response response) {
        if (!Objects.equals(idMessage, response.getId()) || !Objects.equals(message.getTitle(), response.getTitle()) ||
                !Objects.equals(message.getImportant_numbers(), response.getImportant_numbers())) {
            return false;
        } else {
            return true;
        }
    }


}
