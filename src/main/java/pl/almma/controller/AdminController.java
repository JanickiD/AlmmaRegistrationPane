package pl.almma.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.almma.model.User;
import pl.almma.repository.ClubRepository;
import pl.almma.service.UserService;
import pl.almma.tools.PeselValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	UserService userService;
	ClubRepository clubRepository;
	
	@Autowired
	public AdminController(UserService userService, ClubRepository clubRepository) {
		super();
		this.userService = userService;
		this.clubRepository = clubRepository;
	}


	@GetMapping("/panel")
	public String panel(Model model, Pageable pageable) {
		
		model.addAttribute("users", userService.getAll(pageable));
		
		
		return "/admin/panel";
	}

	@GetMapping("/view/{id}")
	public String viewProfile(@PathVariable long id, Model model) {
		
		model.addAttribute("user", userService.findById(id));
		
		return "/admin/view";
	}
	
	@GetMapping("/userEdit/{id}")
	public String editProfile(@PathVariable long id, Model model) {
		
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("clubList", clubRepository.findAll());
		model.addAttribute("roles", userService.getAllRoles());
		
		return "/admin/userEdit";
	}
	
	@PostMapping("/userEdit")
	public String saveEditedProfile(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, Pageable pageable) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("status", "Błędne dane. Zweryfikuj ");
			return "/admin/userEdit";
		}
		model.addAttribute("users", userService.getAll(pageable));
		PeselValidator peselValidator = new PeselValidator(user.getPesel());
		
		if (!peselValidator.isValid()) {
			model.addAttribute("status", "Niepoprawny Pesel. Zweryfikuj ");
			return "/admin/panel";
		}
		
		userService.editUser(user);
		model.addAttribute("status", "Dane zawodnika zmodyfikowane poprawnie!");
		
		return "/admin/panel";
	}
}
