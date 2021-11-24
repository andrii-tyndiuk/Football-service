package spring.projects.footballmanager.service.mapper.impl;

import org.springframework.stereotype.Component;
import spring.projects.footballmanager.dto.request.PlayerRequestDto;
import spring.projects.footballmanager.dto.response.FootballTeamShortResponseDto;
import spring.projects.footballmanager.dto.response.PlayerFullResponseDto;
import spring.projects.footballmanager.models.FootballTeam;
import spring.projects.footballmanager.models.Player;
import spring.projects.footballmanager.service.FootballTeamService;
import spring.projects.footballmanager.service.mapper.RequestDtoMapper;
import spring.projects.footballmanager.service.mapper.ResponseDtoMapper;

@Component
public class PlayerMapper implements RequestDtoMapper<PlayerRequestDto, Player>,
        ResponseDtoMapper<PlayerFullResponseDto, Player> {
    private FootballTeamService footballTeamService;
    private ResponseDtoMapper<FootballTeamShortResponseDto, FootballTeam> teamMapper;

    public PlayerMapper(FootballTeamService footballTeamService,
                        ResponseDtoMapper<FootballTeamShortResponseDto, FootballTeam> teamMapper) {
        this.footballTeamService = footballTeamService;
        this.teamMapper = teamMapper;
    }

    @Override
    public Player mapToModel(PlayerRequestDto dto) {
        Player player = new Player();
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setCareerStart(dto.getCareerStart());
        player.setBirthday(dto.getBirthday());
        player.setFootballTeam(footballTeamService.get(dto.getFootballTeamId()));
        return player;
    }

    @Override
    public PlayerFullResponseDto mapToDto(Player player) {
        PlayerFullResponseDto responseDto = new PlayerFullResponseDto();
        responseDto.setId(player.getId());
        responseDto.setFirstName(player.getFirstName());
        responseDto.setLastName(player.getLastName());
        responseDto.setCareerStart(player.getCareerStart());
        responseDto.setBirthday(player.getBirthday());
        responseDto.setTeamShortResponseDto(teamMapper.mapToDto(player.getFootballTeam()));
        return responseDto;
    }
}