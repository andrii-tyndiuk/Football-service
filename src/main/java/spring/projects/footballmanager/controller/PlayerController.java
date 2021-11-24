package spring.projects.footballmanager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.projects.footballmanager.dto.request.PlayerRequestDto;
import spring.projects.footballmanager.dto.response.PlayerFullResponseDto;
import spring.projects.footballmanager.dto.response.PlayerShortResponseDto;
import spring.projects.footballmanager.models.Player;
import spring.projects.footballmanager.service.PlayerService;
import spring.projects.footballmanager.service.mapper.RequestDtoMapper;
import spring.projects.footballmanager.service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private PlayerService playerService;
    private ResponseDtoMapper<PlayerShortResponseDto, Player> shortResponseDtoMapper;
    private ResponseDtoMapper<PlayerFullResponseDto, Player> responseDtoMapper;
    private RequestDtoMapper<PlayerRequestDto, Player> requestDtoMapper;

    public PlayerController(PlayerService playerService,
                            ResponseDtoMapper<PlayerShortResponseDto, Player> shortResponseDtoMapper,
                            ResponseDtoMapper<PlayerFullResponseDto, Player> responseDtoMapper,
                            RequestDtoMapper<PlayerRequestDto, Player> requestDtoMapper) {
        this.playerService = playerService;
        this.shortResponseDtoMapper = shortResponseDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @GetMapping
    public List<PlayerShortResponseDto> getAll() {
        return playerService.getAll()
                .stream()
                .map(shortResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlayerFullResponseDto get(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(playerService.get(id));
    }

    @PostMapping("/save")
    public PlayerFullResponseDto save(@RequestBody @Valid PlayerRequestDto requestDto) {
        return responseDtoMapper.mapToDto(
                playerService.save(requestDtoMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    public PlayerFullResponseDto update(@PathVariable Long id,
                                        @RequestBody @Valid PlayerRequestDto playerRequestDto) {
        Player player = playerService.get(id);
        Player newPlayer = requestDtoMapper.mapToModel(playerRequestDto);
        newPlayer.setId(id);
        newPlayer.setFootballTeam(player.getFootballTeam());
        return responseDtoMapper.mapToDto(playerService.save(newPlayer));
    }

    @PutMapping("/transfer/{playerId}-{newTeamId}")
    public PlayerFullResponseDto transfer(@PathVariable Long playerId,
                                          @PathVariable Long newTeamId) {
        return responseDtoMapper.mapToDto(playerService.transfer(playerId, newTeamId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
