package security.oauth.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import security.oauth.custom_validators.PasswordMatches;
import security.oauth.custom_validators.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

@PasswordMatches //custom annotation
public class ForgotPasswordDto {

    @NotNull
    @NotEmpty
    @ValidPassword//custom annotation
    String password;

    @NotNull
    @NotEmpty
    String confirmPassword;

    public ForgotPasswordDto(@NotNull @NotEmpty String password, @NotNull @NotEmpty String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}