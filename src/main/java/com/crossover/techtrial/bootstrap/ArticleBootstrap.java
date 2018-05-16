package com.crossover.techtrial.bootstrap;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class ArticleBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ArticleRepository articleRepository;

    private static final String[] titles = {
            "Title",
            "Title1",
            "Title2"
    };

    public ArticleBootstrap(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadArticles();
    }

    private void loadArticles() {
        this.articleRepository.deleteAll();
        for (int i = 0; i < 25000; i++) {
            Article article = new Article();
            article.setEmail("heniart.thomas@gmail.com");
            article.setTitle(titles[ThreadLocalRandom.current().nextInt(titles.length)]);
            this.articleRepository.save(article);
        }
    }
}
