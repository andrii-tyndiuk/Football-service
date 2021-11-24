package spring.projects.footballmanager.service;

import java.util.List;
import spring.projects.footballmanager.models.Player;

public interface PlayerService {
    List<Player> getAll();

    Player save(Player player);

    void delete(Long id);

    Player get(Long id);

    Player transfer(Long playerId, Long newTeamId);
}
