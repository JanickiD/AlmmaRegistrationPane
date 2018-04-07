package pl.almma.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.almma.model.Article;
import pl.almma.repository.ArticleRepository;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	private ArticleRepository articleRepository;

	@Autowired
	public ArticleController(ArticleRepository articleRepository) {
		super();
		this.articleRepository = articleRepository;
	}

	@GetMapping("/add")
	public String newArticle(Model model) {

		model.addAttribute("article", new Article());

		return "/articles/add";
	}

	@PostMapping("/add")
	public String addArticle(@Valid @ModelAttribute Article article, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("status", "Błąd w dodawaniu artykułu");
			return "/admin/panel";
		}
		
		articleRepository.save(article);

		return "/admin/panel";
	}
}
