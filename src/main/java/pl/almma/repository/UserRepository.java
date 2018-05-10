package pl.almma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.almma.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findUserByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE role_id = 2")
	List<User> getTrainersList();

}
