package spring.projects.footballmanager.dto.response;

import lombok.Data;

@Data
public class FootballTeamShortResponseDto {
    private Long id;
    private String name;
    private String country;
    private Long budget;
}
