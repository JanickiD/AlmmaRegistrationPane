package pl.almma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.almma.model.Club;

public interface ClubRepository extends JpaRepository<Club, Long>{

	Club findByName(String name);
	
	@Query("Select c from Club c where club_id = ?")
	Club findByClub_id(long id);
	
}
