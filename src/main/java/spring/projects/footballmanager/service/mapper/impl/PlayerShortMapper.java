package spring.projects.footballmanager.service.mapper.impl;

import org.springframework.stereotype.Component;
import spring.projects.footballmanager.dto.response.PlayerShortResponseDto;
import spring.projects.footballmanager.models.Player;
import spring.projects.footballmanager.service.mapper.ResponseDtoMapper;

@Component
public class PlayerShortMapper implements ResponseDtoMapper<PlayerShortResponseDto, Player> {
    @Override
    public PlayerShortResponseDto mapToDto(Player player) {
        PlayerShortResponseDto responseDto = new PlayerShortResponseDto();
        responseDto.setId(player.getId());
        responseDto.setFirstName(player.getFirstName());
        responseDto.setLastName(player.getLastName());
        responseDto.setFootballTeamId(player.getFootballTeam().getId());
        return responseDto;
    }
}
