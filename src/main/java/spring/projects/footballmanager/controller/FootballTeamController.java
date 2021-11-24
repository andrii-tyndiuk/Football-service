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
import spring.projects.footballmanager.dto.request.FootballTeamRequestDto;
import spring.projects.footballmanager.dto.response.FootballTeamFullResponseDto;
import spring.projects.footballmanager.dto.response.FootballTeamShortResponseDto;
import spring.projects.footballmanager.models.FootballTeam;
import spring.projects.footballmanager.service.FootballTeamService;
import spring.projects.footballmanager.service.mapper.RequestDtoMapper;
import spring.projects.footballmanager.service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/team")
public class FootballTeamController {
    private FootballTeamService service;
    private RequestDtoMapper<FootballTeamRequestDto, FootballTeam> requestDtoMapper;
    private ResponseDtoMapper<FootballTeamShortResponseDto, FootballTeam> shortResponseDtoMapper;
    private ResponseDtoMapper<FootballTeamFullResponseDto, FootballTeam> fullResponseDtoMapper;

    public FootballTeamController(FootballTeamService service,
                                  RequestDtoMapper<FootballTeamRequestDto, FootballTeam> requestDtoMapper,
                                  ResponseDtoMapper<FootballTeamShortResponseDto, FootballTeam>
                                          shortResponseDtoMapper,
                                  ResponseDtoMapper<FootballTeamFullResponseDto, FootballTeam>
                                          fullResponseDto) {
        this.service = service;
        this.requestDtoMapper = requestDtoMapper;
        this.shortResponseDtoMapper = shortResponseDtoMapper;
        this.fullResponseDtoMapper = fullResponseDto;
    }

    @GetMapping
    public List<FootballTeamShortResponseDto> getAll() {
        return service.getAll()
                .stream()
                .map(shortResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FootballTeamFullResponseDto get(@PathVariable Long id) {
        return fullResponseDtoMapper.mapToDto(service.get(id));
    }

    @PostMapping("/save")
    public FootballTeamFullResponseDto save(@RequestBody @Valid FootballTeamRequestDto teamRequestDto) {
        return fullResponseDtoMapper.mapToDto(
                service.save(requestDtoMapper.mapToModel(teamRequestDto)));
    }

    @PutMapping("/{id}")
    public FootballTeamFullResponseDto update(@PathVariable Long id,
                                              @RequestBody @Valid FootballTeamRequestDto teamRequestDto) {
        FootballTeam newFootballTeam = requestDtoMapper.mapToModel(teamRequestDto);
        newFootballTeam.setId(id);
        FootballTeam oldFootballTeam = service.get(id);
        newFootballTeam.setPlayers(oldFootballTeam.getPlayers());
        return fullResponseDtoMapper.mapToDto(service.save(newFootballTeam));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
