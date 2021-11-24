package spring.projects.footballmanager.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import lombok.Data;

@Entity
@Data
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @PastOrPresent
    private LocalDate careerStart;
    @Past
    private LocalDate birthday;
    @ManyToOne
    private FootballTeam footballTeam;
}
