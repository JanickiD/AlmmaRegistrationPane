package pl.almma.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.almma.model.Player;
import pl.almma.model.Role;
import pl.almma.repository.PlayerRepository;
import pl.almma.repository.RoleRepository;

@Service
public class PlayerService {

	private PlayerRepository playerRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public PlayerService(PlayerRepository playerRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.playerRepository = playerRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public Player addUserWithRole(Player player) {

		
		Role role = player.getRole();
		player.setActive(true);
		player.setRole(role);
		player.setPass(bCryptPasswordEncoder.encode(player.getPass()));

		return playerRepository.save(player);
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();

	}

	/*
	 * metoda zwraca wszystkie rodzaje użytkowników oprócz admina. Wykorzysytwana
	 * jest podczas rejestracji. Pokazuje nowo rejestrującym się użytkownikom jakie
	 * role mogą wybrać
	 */
	public List<Role> getAllRolesExceptAdmin() {
		return roleRepository.findAllRolesExceptAdmin();
	}

	/*
	 * private Role getUserRole() { Role role = roleRepository.findByRole("Player");
	 * if (Objects.isNull(role)) { role = roleRepository.save(new Role("Player")); }
	 * return role; }
	 */
}
