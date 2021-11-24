package spring.projects.footballmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.projects.footballmanager.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
