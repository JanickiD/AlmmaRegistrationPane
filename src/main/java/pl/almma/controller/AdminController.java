package pl.almma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.almma.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	UserService userService;
	
	@Autowired
	public AdminController(UserService userService) {
		super();
		this.userService = userService;
	}


	@GetMapping("/panel")
	public String panel(Model model, Pageable pageable) {
		
		model.addAttribute("users", userService.getAll(pageable));
		
		
		return "/admin/panel";
	}

}
