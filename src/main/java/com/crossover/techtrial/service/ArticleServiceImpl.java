package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Title;
import com.crossover.techtrial.repository.ArticleRepository;
import com.crossover.techtrial.repository.TitleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private TitleRepository titleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, TitleRepository titleRepository) {
        this.articleRepository = articleRepository;
        this.titleRepository = titleRepository;
    }

    @Transactional
    public Article save(Article article) {
        Title title = article.getTitle();
        if (!titleRepository.findById(title.getId()).isPresent())
            article.setTitle(titleRepository.save(title));
        return articleRepository.save(article);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public List<Article> search(String search) {
        return articleRepository.findTop10ByTitleIdContainingIgnoreCase(search);
    }

}