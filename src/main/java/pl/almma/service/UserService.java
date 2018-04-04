package pl.almma.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.almma.model.Role;
import pl.almma.model.User;
import pl.almma.repository.RoleRepository;
import pl.almma.repository.UserRepository;
import pl.almma.tools.PeselValidator;

@Service
public class UserService {

	PeselValidator peselValidator;
	SecurityContextHolder securityContextHolder;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,  SecurityContextHolder securityContextHolder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.securityContextHolder = securityContextHolder;
		
	}

	public User addUserWithRole(User user) {

		Date birthDate = getBirthDateFromPesel(user);
				
		Role role = user.getRole();
		user.setActive(true);
		user.setRole(role);
		user.setBirthDate(birthDate);
		user.setPass(bCryptPasswordEncoder.encode(user.getPass()));

		return userRepository.save(user);

	}

	private Date getBirthDateFromPesel(User user) {
		
		PeselValidator ps = new PeselValidator(user.getPesel());
		int birthYear = ps.getBirthYear();
		int birthMonth = ps.getBirthMonth();
		int birthDay = ps.getBirthDay();
		
		StringBuilder ds = new StringBuilder();
		ds.append(birthYear);
		ds.append("-");
		ds.append(birthMonth);
		ds.append("-");
		ds.append(birthDay);
		ds.append("-");
		
		String date = ds.toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = null;
		try {
			birthDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return birthDate;
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();

	}
	
	public User findById(long id) {
		return userRepository.findOne(id);
	}
	
	
	public User findLoggedUser() {
		User loggedUser = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return loggedUser;
	}
	

	

	/*
	 * metoda zwraca wszystkie rodzaje użytkowników oprócz admina. Wykorzysytwana
	 * jest podczas rejestracji. Pokazuje nowo rejestrującym się użytkownikom jakie
	 * role mogą wybrać
	 */
	public List<Role> getAllRolesExceptAdmin() {
		return roleRepository.findAllRolesExceptAdmin();
	}
	
	public User setprofileImageFileName(User user, String fileName) {
		user.setProfileImageFileName(fileName);
		return userRepository.save(user);
	}
	

	
	
	public User editUser(User user) {
		User findById = findById(user.getId());
		user.setPass(findById.getPass());
		user.setBirthDate(getBirthDateFromPesel(user));
			
		return userRepository.save(user);
	}

	
	public Page<User> getAll(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
}
