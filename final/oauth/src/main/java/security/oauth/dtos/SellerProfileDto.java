package security.oauth.dtos;

import security.oauth.custom_validators.ValidGST;

import javax.validation.constraints.NotEmpty;

public class SellerProfileDto {
private Long id;
@NotEmpty
private String firstName;
private String lastName;
@ValidGST
private String gst;
   @NotEmpty
    private String companyName;

   private String companyContact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
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

    public SellerProfileDto(Long id, String firstName, String lastName, String gst, String companyName, String companyContact) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gst = gst;
        this.companyName = companyName;
        this.companyContact = companyContact;
    }

    public SellerProfileDto() {
    }
}
