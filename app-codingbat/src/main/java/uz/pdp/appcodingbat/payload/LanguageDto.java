package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {
    @NotNull(message = "Language name should not be empty")
    private String name;

}
