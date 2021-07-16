package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    @NotNull(message = "Text should not be empty")
    private String text;

    @NotNull(message = "Task id should not be empty")
    private Integer taskId;

    @NotNull(message = "User id should not be empty")
    private Integer userId;

    @NotNull(message = "Is correct should not be empty")
    private boolean isCorrect;

}
