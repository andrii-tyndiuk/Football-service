package spring.projects.footballmanager.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PlayerRequestDto {
    private String firstName;
    private String lastName;
    private LocalDate careerStart;
    private LocalDate birthday;
    private Long footballTeamId;
}
