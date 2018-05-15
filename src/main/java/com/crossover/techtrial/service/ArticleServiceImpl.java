package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public List<Article> search(String search) {
        //return articleRepository.findTop10ByTitleContainingIgnoreCase(search);
        //return articleRepository.findAllByTitleContainingIgnoreCase(search);
        return articleRepository.findAllByTitleIdContainingIgnoreCase(search);
    }

}