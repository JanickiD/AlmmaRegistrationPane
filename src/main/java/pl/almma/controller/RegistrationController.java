package pl.almma.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.almma.model.User;
import pl.almma.repository.ClubRepository;
import pl.almma.service.UserService;
import pl.almma.tools.PeselValidator;

@Controller
public class RegistrationController {

	private UserService userService;
	private ClubRepository clubRepository;
	private PeselValidator peselValidator;

	@Autowired
	public RegistrationController(UserService userService, ClubRepository clubRepository) {
		super();
		this.userService = userService;
		this.clubRepository = clubRepository;
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		

		
		model.addAttribute("user", new User());
		model.addAttribute("roles", userService.getAllRolesExceptAdmin());
		model.addAttribute("clubList", clubRepository.findAll());

		return "registration";

	}

	@PostMapping("/register")
	public String save(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "index";

		}
		try {
			PeselValidator PESEL = new PeselValidator(user.getPesel());
			if (PESEL.isValid()) {
				User savedUser = userService.addUserWithRole(user);
				System.out.println("dodany: " + savedUser);

				model.addAttribute("status", "Rejestracja pomyślna!");
				
				return "login";
			} else {
				model.addAttribute("status", "Rejestracja nieudana! Sprawdz dane");
				model.addAttribute("user", user);
				
			}

			return "registration";
		} catch (DuplicateKeyException e) {
			model.addAttribute("status", "Rejestracja nieudana! Sprawdz dane");
			model.addAttribute("user", user);
			
			return "redirect:registration";
		}

	}

	@ExceptionHandler(DuplicateKeyException.class)
	public String handleDuplicateEntry() {
		return "Email już zarejestrowany!";
	}
}
