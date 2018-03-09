package pl.almma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.almma.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
 
	
	Role findByRole(String role);	
	
	
	@Query("SELECT r FROM Role r WHERE id <> 3")
	List<Role> findAllRolesExceptAdmin();
	
}
