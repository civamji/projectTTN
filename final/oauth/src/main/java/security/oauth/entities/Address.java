package security.oauth.entities;


import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String city;
    private String state;
    private String country;
    private String addressLine;
    private String zipCode;
    private String label;
private Boolean isDeleted=false;
@ManyToOne
@JoinColumn(name = "customer_user_id")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", label='" + label + '\'' +
                ", isDeleted=" + isDeleted +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Address(Long id, String city, String state, String country, String addressLine, String zipCode, String label, Boolean isDeleted) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.addressLine = addressLine;
        this.zipCode = zipCode;
        this.label = label;
        this.isDeleted = isDeleted;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Address() {
    }


}
