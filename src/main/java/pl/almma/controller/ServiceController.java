package pl.almma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.almma.service.PlayerService;

@Controller
public class ServiceController {

	@Autowired
	public ServiceController(PlayerService playerService) {
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

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
