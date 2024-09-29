package pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO класс, представляющий сущность ResponseAddition.
 * <p>
 * Этот класс используется для хранения информации о добавочных полях
 * сообщения. Он включает в себя идентификатор, дополнительную информацию
 * и дополнительный номер.
 * </p>
 */
@Getter
@Setter
@Builder
public class ResponseAddition {
    /**
     * Уникальный идентификатор объекта ResponseAddition.
     */
    private String id;
    /**
     * Дополнительная информация, связанная с объектом ResponseAddition.
     * <p>
     * Поле сериализуется из JSON как "additional_info".
     * </p>
     */
    @SerializedName("additional_info")
    private String additionalInfo;
    /**
     * Дополнительный номер, связанный с объектом ResponseAddition.
     * <p>
     * Поле сериализуется из JSON как "additional_number".
     * </p>
     */
    @SerializedName("additional_number")
    private Integer additionalNumber;
}
