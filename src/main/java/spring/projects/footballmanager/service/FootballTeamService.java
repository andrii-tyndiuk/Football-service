package spring.projects.footballmanager.service;

import java.util.List;
import spring.projects.footballmanager.models.FootballTeam;

public interface FootballTeamService {
    List<FootballTeam> getAll();

    FootballTeam save(FootballTeam footballTeam);

    void delete(Long id);

    FootballTeam get(Long id);

}
