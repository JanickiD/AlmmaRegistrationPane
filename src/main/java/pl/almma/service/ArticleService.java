package pl.almma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.almma.model.Article;
import pl.almma.repository.ArticleRepository;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	private UserService userService;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository, UserService userService) {
		super();
		this.articleRepository = articleRepository;
		this.userService = userService;
	}

	public Page<Article> getAll(Pageable pageable){
		return articleRepository.findAll(pageable);
	}
	
	public Article saveArticle(Article article) {
		article.setAuthor(userService.findLoggedUser());
		return articleRepository.save(article);
		
	}
	
	public Article findById(long id) {
		return articleRepository.findOne(id);
	}

}
