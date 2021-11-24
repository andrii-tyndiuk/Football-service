package spring.projects.footballmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.projects.footballmanager.models.FootballTeam;

@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {

}
