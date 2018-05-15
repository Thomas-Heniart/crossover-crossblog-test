package com.crossover.techtrial.bootstrap;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Title;
import com.crossover.techtrial.repository.ArticleRepository;
import com.crossover.techtrial.repository.TitleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class ArticleBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ArticleRepository articleRepository;

    private TitleRepository titleRepository;

    public ArticleBootstrap(ArticleRepository articleRepository, TitleRepository titleRepository) {
        this.articleRepository = articleRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadArticles();
    }

    private void loadArticles() {
        this.articleRepository.deleteAll();
        titleRepository.deleteAll();
        Title title = new Title();
        title.setId("Test");
        titleRepository.save(title);
        Title title1 = new Title();
        title1.setId("Test1");
        titleRepository.save(title1);
        Title title2 = new Title();
        title2.setId("Test2");
        titleRepository.save(title2);
        for (int i = 0; i < 10000; i++) {
            Article article = new Article();
            article.setEmail("heniart.thomas@gmail.com");
            switch (ThreadLocalRandom.current().nextInt(3)) {
                case 0:
                    article.setTitle(title);
                    break;
                case 1:
                    article.setTitle(title1);
                    break;
                case 2:
                    article.setTitle(title2);
                    break;
                default:
                    break;
            }
            this.articleRepository.save(article);
        }
    }
}
