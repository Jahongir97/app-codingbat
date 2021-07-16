package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @NotNull(message = "Language name should not be empty")
    private String name;
    @NotNull(message = "Description should not be empty")
    private String description;
    @NotNull(message = "Language Id should not be empty")
    private Integer languageId;
}
