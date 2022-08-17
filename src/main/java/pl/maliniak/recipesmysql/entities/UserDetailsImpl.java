package pl.maliniak.recipesmysql.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.maliniak.recipesmysql.entities.User;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> rolesAndAuthorities;


    public UserDetailsImpl(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        rolesAndAuthorities = List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesAndAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
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
