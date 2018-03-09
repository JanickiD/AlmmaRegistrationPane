package pl.almma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.almma.model.Competition;
import pl.almma.service.CompetitionService;

@Controller
public class CompetitionController {

	private CompetitionService competitionService;

	@Autowired
	public CompetitionController(CompetitionService competitionService) {
		super();
		this.competitionService = competitionService;
	}

	@GetMapping("/calendar")
	public String show(Model model) {

		List<Competition> allCompetitions = competitionService.getAllCompetitions();
		model.addAttribute("competitions", allCompetitions);

		return "calendar";

	}

}
