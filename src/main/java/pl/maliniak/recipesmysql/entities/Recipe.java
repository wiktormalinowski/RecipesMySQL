package pl.maliniak.recipesmysql.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RECIPES")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "Recipe_ID")
    Long id;


    @NotBlank
    String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "User_ID")
    User user;

    @NotBlank
    String category;

    @CreatedDate
    LocalDateTime date;

    @NotBlank
    String description;


    @ElementCollection
    @CollectionTable(name = "INGREDIENTS", joinColumns = @JoinColumn(name = "Recipe_ID"))
    @Column(name = "Ingredient")
    @NotEmpty
    List<String> ingredients;


    @ElementCollection
    @CollectionTable(name = "DIRECTIONS", joinColumns = @JoinColumn(name = "Recipe_ID"))
    @Column(name = "Direction")
    @NotEmpty
    List<String> directions;


    @PrePersist
    @PreUpdate
    public void prePersist() {
        date = LocalDateTime.now();
    }


}
