package pl.maliniak.recipesmysql.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.maliniak.recipesmysql.entities.User;
import pl.maliniak.recipesmysql.repository.UserRepo;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User findUserByEmail(String name) {
        return userRepo.findUserByEmail(name);
    }

}