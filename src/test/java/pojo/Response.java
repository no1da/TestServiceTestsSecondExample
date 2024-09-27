package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
/**
 * Pojo класс
 * Сущность pojo.Response
 */
public class Response {
    /**
     * Verified
     */
    boolean verified;
    /**
     * id pojo.Response
     */
    private String id;
    /**
     * pojo.Addition
     */
    private ResponseAddition responseAddition;
    /**
     * important numbers
     */
    private List<Integer> important_numbers;
    /**
     * Title
     */
    private String title;
}
