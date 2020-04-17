package security.oauth.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AppUser implements UserDetails {


    private String username;
    private String password;
    List<SimpleGrantedAuthority> grantedAuthorities;
    private boolean isActive;
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonLocked;

//    private Set<Role> roles;
//
//    private Set<Address> addresses;


    public AppUser(String username, String password, List<SimpleGrantedAuthority> grantedAuthorities, boolean isActive, boolean isCredentialsNonExpired, boolean isAccountNonLocked) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isActive = isActive;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grantedAuthorities=" + grantedAuthorities +
                ", isActive=" + isActive +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                '}';
    }
}