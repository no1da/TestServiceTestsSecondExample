package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Pojo класс
 * Сущность pojo.ResponseAddition
 */
@Getter
@Setter
@Builder
public class ResponseAddition {
    /**
     * id pojo.ResponseAddition
     */
    private String id;
    /**
     * pojo.Addition info
     */
    private String additional_info;
    /**
     * Additional number
     */
    private Integer additional_number;
}
