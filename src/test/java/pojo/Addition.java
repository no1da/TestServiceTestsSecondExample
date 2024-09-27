package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Pojo класс
 * Сущность pojo.Addition
 */
@Getter
@Setter
@Builder
public class Addition {
    /**
     * pojo.Addition info
     */
    @Builder.Default
    private String additional_info = "Дополнительные сведения";
    /**
     * Additional number
     */
    @Builder.Default
    private Integer additional_number = 123;
}
