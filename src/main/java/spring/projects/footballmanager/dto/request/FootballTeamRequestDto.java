package spring.projects.footballmanager.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class FootballTeamRequestDto {
    @NotBlank
    private String name;
    private String country;
    private String city;
    private String description;
    @PositiveOrZero
    private Long budget;
    @PositiveOrZero
    @Max(value = 10)
    private int commissionPercentage;
}
