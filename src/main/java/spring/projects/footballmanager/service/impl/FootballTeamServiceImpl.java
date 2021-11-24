package spring.projects.footballmanager.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import spring.projects.footballmanager.models.FootballTeam;
import spring.projects.footballmanager.repository.FootballTeamRepository;
import spring.projects.footballmanager.service.FootballTeamService;

@Service
public class FootballTeamServiceImpl implements FootballTeamService {
    private FootballTeamRepository teamRepository;

    public FootballTeamServiceImpl(FootballTeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<FootballTeam> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public FootballTeam save(FootballTeam footballTeam) {
        return teamRepository.save(footballTeam);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public FootballTeam get(Long id) {
        return teamRepository.findById(id).orElseThrow();
    }
}
