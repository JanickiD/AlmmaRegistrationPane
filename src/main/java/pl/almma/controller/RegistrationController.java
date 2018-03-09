package pl.almma.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.almma.model.Player;
import pl.almma.service.PlayerService;
import pl.almma.tools.PeselValidator;

@Controller
public class RegistrationController {

	private PlayerService playerService;

	@Autowired
	public RegistrationController(PlayerService playerService) {
		super();
		this.playerService = playerService;
	}

	@GetMapping("/registration")
	public String registration(Model model) {

		model.addAttribute("user", new Player());
		model.addAttribute("roles", playerService.getAllRolesExceptAdmin());

		return "registration";

	}

	@PostMapping("/register")
	public String save(@Valid @ModelAttribute Player player, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "index";

		}
		
		PeselValidator PESEL = new PeselValidator(player.getPesel());
		if(PESEL.isValid()) {
			Player savedUser = playerService.addUserWithRole(player);
			System.out.println("dodany: " + savedUser);

			model.addAttribute("status", "Rejestracja pomyślna!");
			model.addAttribute("user", new Player());
		} else {
			model.addAttribute("status", "Rejestracja nieudana! Sprawdz dane");
			model.addAttribute("user", player);
		}

		

		return "registration";

	}
}
