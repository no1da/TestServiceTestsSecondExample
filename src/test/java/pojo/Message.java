package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Pojo класс
 * Сущность pojo.Message
 */
@Getter
@Setter
@Builder
public class Message {
    /**
     * pojo.Addition
     */
    @Builder.Default
    private Addition addition = new Addition("Дополнительные сведения", 123);
    /**
     * important numbers
     */
    @Builder.Default
    private List<Integer> important_numbers = List.of(42,87,15);
    /**
     * Title
     */
    @Builder.Default
    private String title = "Заголовок сущности";
    /**
     * Verified
     */
    @Builder.Default
    boolean verified = true;
}
