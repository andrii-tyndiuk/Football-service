package spring.projects.footballmanager.dto.response;

import java.util.Set;
import lombok.Data;

@Data
public class FootballTeamFullResponseDto {
    private Long id;
    private String name;
    private String country;
    private Long budget;
    private String city;
    private String description;
    private Set<PlayerShortResponseDto> playerShortResponseDtos;
    private int commissionPercentage;
}
