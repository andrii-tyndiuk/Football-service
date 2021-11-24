package spring.projects.footballmanager.service.mapper.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import spring.projects.footballmanager.dto.request.FootballTeamRequestDto;
import spring.projects.footballmanager.dto.response.FootballTeamFullResponseDto;
import spring.projects.footballmanager.dto.response.PlayerShortResponseDto;
import spring.projects.footballmanager.models.FootballTeam;
import spring.projects.footballmanager.models.Player;
import spring.projects.footballmanager.service.mapper.RequestDtoMapper;
import spring.projects.footballmanager.service.mapper.ResponseDtoMapper;

@Component
public class FootballTeamMapper implements RequestDtoMapper<FootballTeamRequestDto, FootballTeam>,
        ResponseDtoMapper<FootballTeamFullResponseDto, FootballTeam> {
    private ResponseDtoMapper<PlayerShortResponseDto, Player> playerResponseDtoMapper;

    public FootballTeamMapper(ResponseDtoMapper<PlayerShortResponseDto, Player> responseDtoMapper) {
        this.playerResponseDtoMapper = responseDtoMapper;
    }

    @Override
    public FootballTeam mapToModel(FootballTeamRequestDto dto) {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setName(dto.getName());
        footballTeam.setCountry(dto.getCountry());
        footballTeam.setCity(dto.getCity());
        footballTeam.setDescription(dto.getDescription());
        footballTeam.setBudget(dto.getBudget());
        footballTeam.setCommissionPercentage(dto.getCommissionPercentage());
        return footballTeam;
    }

    @Override
    public FootballTeamFullResponseDto mapToDto(FootballTeam footballTeam) {
        FootballTeamFullResponseDto fullResponseDto = new FootballTeamFullResponseDto();
        fullResponseDto.setId(footballTeam.getId());
        fullResponseDto.setName(footballTeam.getName());
        fullResponseDto.setCountry(footballTeam.getCountry());
        fullResponseDto.setBudget(footballTeam.getBudget());
        fullResponseDto.setCity(footballTeam.getCity());
        fullResponseDto.setDescription(footballTeam.getDescription());
        Optional<Set<Player>> optional = Optional.ofNullable(footballTeam.getPlayers());
        optional.ifPresent(o -> fullResponseDto.setPlayerShortResponseDtos(o.stream()
                        .map(playerResponseDtoMapper::mapToDto)
                        .collect(Collectors.toSet())));
        fullResponseDto.setCommissionPercentage(footballTeam.getCommissionPercentage());
        return fullResponseDto;
    }
}
