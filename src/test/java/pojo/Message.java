package pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJO класс, представляющий сущность Message.
 * <p>
 * Этот класс используется для хранения информации о сообщении,
 * включая его заголовок, подтвержденность, важные числа и дополнительные поля.
 * </p>
 */
@Getter
@Setter
@Builder
public class Message {
    /**
     * Дополнительная информация, связанная с сообщением.
     */
    private Addition addition;
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
    /**
     * Флаг, указывающий, было ли сообщение проверено.
     */
    private boolean verified;
}
