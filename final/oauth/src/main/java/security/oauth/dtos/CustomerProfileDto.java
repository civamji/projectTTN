package security.oauth.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerProfileDto {
@NotEmpty
@NotNull
    private Long id;
@NotNull
private String firstName;
private String lastName;
private Boolean isActive=true;
@NotNull
private String contact;
private String image;


}
