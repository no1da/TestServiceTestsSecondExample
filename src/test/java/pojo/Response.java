package pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * POJO класс, представляющий сущность Response.
 * <p>
 * Этот класс используется для хранения ответа от API,
 * который включает в себя информацию о сообщении, включая его
 * заголовок, подтвержденность, важные числа и дополнительные поля.
 * </p>
 */
@Getter
@Setter
@Builder
public class Response {
    /**
     * Флаг подтверждения, указывающий, было ли сообщение проверено.
     */
    boolean verified;
    /**
     * Уникальный идентификатор объекта Response.
     */
    private String id;
    /**
     * Дополнительная информация, связанная с сообщением.
     */
    private ResponseAddition responseAddition;
    /**
     * Список важных чисел, связанных с сообщением.
     * <p>
     * Поле сериализуется из JSON как "important_numbers".
     * </p>
     */
    @SerializedName("important_numbers")
    private List<Integer> importantNumbers;
    /**
     * Заголовок сообщения.
     */
    private String title;
}
