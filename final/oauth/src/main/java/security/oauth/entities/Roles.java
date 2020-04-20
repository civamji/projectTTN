package security.oauth.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Roles implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Roles(){
    }

    public Roles( String authority) {

        this.authority = authority;
    }

    public Roles(Integer id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", users=" +
                '}';
    }
}