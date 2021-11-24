package spring.projects.footballmanager.service.mapper.impl;

import org.springframework.stereotype.Component;
import spring.projects.footballmanager.dto.response.FootballTeamShortResponseDto;
import spring.projects.footballmanager.models.FootballTeam;
import spring.projects.footballmanager.service.mapper.ResponseDtoMapper;

@Component
public class FootballTeamShortMapper implements ResponseDtoMapper<FootballTeamShortResponseDto,
        FootballTeam> {
    @Override
    public FootballTeamShortResponseDto mapToDto(FootballTeam footballTeam) {
        FootballTeamShortResponseDto shortResponseDto = new FootballTeamShortResponseDto();
        shortResponseDto.setId(footballTeam.getId());
        shortResponseDto.setName(footballTeam.getName());
        shortResponseDto.setCountry(footballTeam.getCountry());
        shortResponseDto.setBudget(footballTeam.getBudget());
        return shortResponseDto;
    }
}
