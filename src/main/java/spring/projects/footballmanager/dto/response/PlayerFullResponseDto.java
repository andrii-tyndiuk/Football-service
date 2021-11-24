package spring.projects.footballmanager.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PlayerFullResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate careerStart;
    private LocalDate birthday;
    private FootballTeamShortResponseDto teamShortResponseDto;
}