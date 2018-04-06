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
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		super();
		this.articleRepository = articleRepository;
	}

	public Page<Article> getAll(Pageable pageable){
		return articleRepository.findAll(pageable);
	}

}
