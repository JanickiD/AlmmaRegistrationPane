package pl.almma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.almma.service.UserService;

@Controller
public class ServiceController {

	@Autowired
	public ServiceController(UserService userService) {
		super();
	}

	@GetMapping("/")
	public String start() {
		return "index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
}
