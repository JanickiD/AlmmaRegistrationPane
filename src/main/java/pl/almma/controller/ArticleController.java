package pl.almma.controller;


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

import pl.almma.model.Article;
import pl.almma.service.ArticleService;
import pl.almma.service.UserService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	private UserService userService;
	private ArticleService articleService;


	@Autowired
	public ArticleController(UserService userService,
			ArticleService articleService) {
		super();
		this.userService = userService;
		this.articleService = articleService;
	}

	@GetMapping("/add")
	public String newArticle(Model model) {

		model.addAttribute("article", new Article());


		return "/articles/add";
	}

	@PostMapping("/add")
	public String addArticle(@ModelAttribute Article article, BindingResult bindingResult, Model model, Pageable pageable) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("article", article);
			model.addAttribute("status", "Błąd w dodawaniu artykułu!");
			return "/articles/add";
		}
		
		articleService.saveArticle(article);
		model.addAttribute("users", userService.getAll(pageable));
		model.addAttribute("articles", articleService.getAll(pageable));

		return "/admin/panel";
	}
	
	@GetMapping("view/{id}")
	public String viewArticle(@PathVariable long id, Model model) {
		model.addAttribute("article", articleService.findById(id));
		return "/articles/view";
	}
	
	
}
