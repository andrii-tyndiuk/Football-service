package spring.projects.footballmanager.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.projects.footballmanager.models.FootballTeam;
import spring.projects.footballmanager.models.Player;
import spring.projects.footballmanager.repository.PlayerRepository;
import spring.projects.footballmanager.service.FootballTeamService;
import spring.projects.footballmanager.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;
    private FootballTeamService footballTeamService;

    public PlayerServiceImpl(PlayerRepository playerRepository,
                             FootballTeamService footballTeamService) {
        this.playerRepository = playerRepository;
        this.footballTeamService = footballTeamService;
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Player get(Long id) {
        return playerRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Player transfer(Long playerId, Long newTeamId) {
        Player player = playerRepository.getById(playerId);
        FootballTeam oldTeam = player.getFootballTeam();
        FootballTeam newTeam = footballTeamService.get(newTeamId);
        Long experienceInMonth = ChronoUnit.MONTHS.between(player.getCareerStart(), LocalDate.now());
        Long age = ChronoUnit.YEARS.between(player.getBirthday(), LocalDate.now());
        Long transferCost = experienceInMonth * 100000 / age;
        Long commission = transferCost * oldTeam.getCommissionPercentage() / 100;
        oldTeam.setBudget(oldTeam.getBudget() + transferCost + commission);
        newTeam.setBudget(newTeam.getBudget() - transferCost - commission);
        player.setFootballTeam(newTeam);
        footballTeamService.save(oldTeam);
        footballTeamService.save(newTeam);
        return save(player);
    }
}
