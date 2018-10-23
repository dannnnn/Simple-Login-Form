package core;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;


//@Table(name = "User")
public class User implements UserDetails {

    private Long id;

    @NotNull(message = "Username cant be null")
    @NotEmpty(message = "")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
    private String username;

    @NotNull(message = "Password cant be null")
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 characters long.")
    private String password;

    @NotNull(message = "First Name cant be null")
    @Size(min = 2, max = 30, message = "First Name must be between 2 and 30 characters long.")
    private String first_name;

    @NotNull(message = "Last Name cant be null")
    @Size(min = 2, max = 30, message = "Last Name must be between 2 and 30 characters long.")
    private String last_name;

    public User() {
    }

    public User(Long id, String username, String password, String first_name, String last_name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public User(String username, String password, String first_name, String last_name) {
        this(null, username, password, first_name, last_name);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getPassword() {
        return password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

