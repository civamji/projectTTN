package security.oauth.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Getter
@Setter
public class CustomerRegistrationDto extends UserRegistrationDto{
@NotNull
@NotEmpty
@Size(min=10,max=10)
    private String contact;
}
