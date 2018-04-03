package pl.almma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.almma.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
