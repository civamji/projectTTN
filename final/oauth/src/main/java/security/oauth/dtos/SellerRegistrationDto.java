package security.oauth.dtos;


import lombok.Getter;
import lombok.Setter;
import security.oauth.custom_validators.ValidGST;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SellerRegistrationDto extends UserRegistrationDto{

    @NotNull
    @NotEmpty
    @Size(min = 15, max = 15)

    //@ValidGST
    private String gst;

    @NotNull
    @NotEmpty
    private String companyName;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 10)
    private String companyContact;

    public String getgst() {
        return gst;
    }

    public void setgst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }
}
