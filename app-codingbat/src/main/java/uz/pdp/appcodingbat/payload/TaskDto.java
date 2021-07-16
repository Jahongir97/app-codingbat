package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcodingbat.entitiy.Language;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    @NotNull(message = "Task name should not be empty")
    private String name;

    @NotNull(message = "Text should not be empty")
    private String text;

    @NotNull(message = "Solution should not be empty")
    private String solution;

    @NotNull(message = "Has star should not be empty")
    private boolean hasStar;

    @NotNull(message = "Hint should not be empty")
    private String hint;

    @NotNull(message = "Method should not be empty")
    private String method;

    @NotNull(message = "Category id should not be empty")
    private Integer categoryId;

}
