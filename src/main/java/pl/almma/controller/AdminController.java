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

import pl.almma.model.Club;
import pl.almma.model.User;
import pl.almma.service.ArticleService;
import pl.almma.service.ClubService;
import pl.almma.service.UserService;
import pl.almma.tools.PeselValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserService userService;
	private ClubService clubService;
	private ArticleService articleService;

	@Autowired
	public AdminController(UserService userService, ClubService clubService, ArticleService articleService) {
		super();
		this.userService = userService;
		this.clubService = clubService;
		this.articleService = articleService;
	}

	@GetMapping("/panel")
	public String showPanel(Model model, Pageable pageable) {

		model.addAttribute("clubs", clubService.getAll(pageable));
		model.addAttribute("users", userService.getAll(pageable));
		model.addAttribute("articles", articleService.getAll(pageable));

		return "/admin/panel";
	}

	@GetMapping("/userView/{id}")
	public String viewProfile(@PathVariable long id, Model model) {

		model.addAttribute("user", userService.findById(id));

		return "/admin/userView";
	}

	@GetMapping("/userEdit/{id}")
	public String editUserProfile(@PathVariable long id, Model model) {

		model.addAttribute("user", userService.findById(id));
		model.addAttribute("clubList", clubService.getAll());
		model.addAttribute("roles", userService.getAllRoles());

		return "/admin/userEdit";
	}

	@PostMapping("/userEdit")
	public String saveEditedUserProfile(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model,
			Pageable pageable) {

		if (bindingResult.hasErrors()) {
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

	@GetMapping("/clubView/{id}")
	public String viewClub(@PathVariable long id, Model model) {

		model.addAttribute("club", clubService.findById(id));

		return "/admin/clubView";
	}

	@GetMapping("/clubEdit/{id}")
	public String editClub(@PathVariable long id, Model model) {

		model.addAttribute("club", clubService.findById(id));
		model.addAttribute("trainers", userService.getTrainersList());

		return "/admin/clubEdit";
	}

	@PostMapping("/clubEdit")
	public String saveEditedClubProfile(@Valid @ModelAttribute Club club, BindingResult bindingResult, Model model,
			Pageable pageable) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("status", "Błędne dane. Zweryfikuj ");
			return "/admin/clubEdit";
			
		}

		return "/admin/panel";
	}
}
