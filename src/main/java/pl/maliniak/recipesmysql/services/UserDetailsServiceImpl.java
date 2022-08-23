package pl.maliniak.recipesmysql.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.maliniak.recipesmysql.entities.UserDetailsImpl;
import pl.maliniak.recipesmysql.entities.User;
import pl.maliniak.recipesmysql.repository.UserRepo;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepo userRepo;


    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("serwis");
        User user = userRepo.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }

        return new UserDetailsImpl(user);
    }
}
