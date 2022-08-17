package pl.maliniak.recipesmysql.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "USERS")

public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Email(regexp = ".+@.+\\..+")
    @NotBlank
    String email;

    @Size(min = 8)
    @NotBlank
    String password;

    String role;
}
