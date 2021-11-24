package spring.projects.footballmanager.dto.response;

import lombok.Data;

@Data
public class PlayerShortResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long footballTeamId;
}
