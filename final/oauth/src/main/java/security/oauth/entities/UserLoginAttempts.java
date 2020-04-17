package security.oauth.entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoginAttempts {
@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private Integer attempts;

    public UserLoginAttempts(Long id, String email, Integer attempts) {
        this.id = id;
        this.email = email;
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "UserLoginAttempts{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", attempts=" + attempts +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public UserLoginAttempts() {
    }
}
