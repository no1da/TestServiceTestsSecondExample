package pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO класс, представляющий сущность Addition.
 * <p>
 * Этот класс используется для хранения дополнительной информации,
 * связанной с сообщением. Он включает в себя поля для дополнительной
 * информации и дополнительного числа.
 * </p>
 */
@Getter
@Setter
@Builder
public class Addition {
    /**
     * Дополнительная информация, связанная с сущностью Addition.
     * <p>
     * Поле сериализуется из JSON как "additional_info".
     * </p>
     */
    @SerializedName("additional_info")
    private String additionalInfo;
    /**
     * Дополнительный номер, связанный с сущностью Addition.
     * <p>
     * Поле сериализуется из JSON как "additional_number".
     * </p>
     */
    @SerializedName("additional_number")
    private Integer additionalNumber;
}
