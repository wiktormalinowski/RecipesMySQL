package pl.maliniak.recipesmysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.maliniak.recipesmysql.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findUserById(Long id);

    User findUserByEmail(String username);
}
