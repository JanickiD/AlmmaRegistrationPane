package pl.almma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.almma.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

	
}
