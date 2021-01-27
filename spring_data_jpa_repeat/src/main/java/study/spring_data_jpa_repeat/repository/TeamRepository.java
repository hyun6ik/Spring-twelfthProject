package study.spring_data_jpa_repeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring_data_jpa_repeat.entitiy.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
