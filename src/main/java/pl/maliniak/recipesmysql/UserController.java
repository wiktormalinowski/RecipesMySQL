package pl.maliniak.recipesmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    UserRepo userService;
    @Autowired
    EncoderConfig encoder;

    @PostMapping("/api/register")
    public ResponseEntity registerUser(@RequestBody @Valid User user) {
        if (userService.findUserByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>("nie mozna rejestrrnąc", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(encoder.passwordEncoder().encode(user.getPassword()));
        user.setRole("USER");
        userService.save(user);
        return new ResponseEntity<>("good", HttpStatus.OK);
    }


}
