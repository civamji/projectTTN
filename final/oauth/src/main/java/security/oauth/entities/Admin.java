package security.oauth.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
//@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User{
    public Admin() {
    }

    public Admin(String email, String firstName, String middleName, String lastName) {
        super(email, firstName, middleName, lastName);
    }
}
