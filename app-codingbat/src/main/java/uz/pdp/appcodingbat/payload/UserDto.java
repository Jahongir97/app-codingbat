package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "Email should not be empty")
    private String email;
    @NotNull(message = "Password should not be empty")
    private String password;
}
