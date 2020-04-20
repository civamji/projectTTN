package security.oauth.dtos;


import lombok.Getter;
import lombok.Setter;
import security.oauth.custom_validators.PasswordMatches;
import security.oauth.custom_validators.ValidEmail;
import security.oauth.custom_validators.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegistrationDto {
    @NotNull
    @NotEmpty
    private String firstName;
    ///optional
    private String middleName;
    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidEmail //Custom annotation
       private String email;

    @NotNull
    @NotEmpty
   // @ValidPassword //custom annotation

    private String password;

    @NotNull
    @NotEmpty
     private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
