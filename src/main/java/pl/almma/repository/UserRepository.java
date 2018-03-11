package pl.almma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.almma.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findUserByEmail(String email);
}
