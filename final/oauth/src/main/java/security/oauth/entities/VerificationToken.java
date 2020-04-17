package security.oauth.entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {
private static final Integer EXPIRATION = 24*60;

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long id;

private String token;

@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
@JoinColumn(nullable = false, name = "user_id")
private User user;

private Date expiryDate;

public VerificationToken() {
        }

public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = this.calculateExpiryDate(EXPIRATION);

        }

public Long getId() {
        return id;
        }

public void setId(Long id) {
        this.id = id;
        }

public String getToken() {
        return token;
        }

public void setToken(String token) {
        this.token = token;
        }

public User getUser() {
        return user;
        }

public void setUser(User user) {
        this.user = user;
        }

public Date getExpiryDate() {
        return expiryDate;
        }

public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        }

public static Integer getEXPIRATION() {
        return EXPIRATION;
        }

private Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
       // cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
        }

    /*public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VerificationToken other = (VerificationToken) obj;
        if (expiryDate == null) {
            if (other.expiryDate != null) {
                return false;
            }
        } else if (!expiryDate.equals(other.expiryDate)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }*/

@Override
public String toString() {
        return "VerificationEntity{" +
        "id=" + id +
        ", token='" + token + '\'' +
        ", user=" + user +
        ", expiryDate=" + expiryDate +
        '}';
        }
        }