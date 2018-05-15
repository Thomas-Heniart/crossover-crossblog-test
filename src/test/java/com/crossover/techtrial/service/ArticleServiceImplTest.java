package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ArticleServiceImplTest {

    private static final Long ARTICLE_ID = 1L;
    private static final String ARTICLE_TITLE = "Title";

    @Mock
    private ArticleRepository articleRepository;

    private ArticleService articleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        articleService = new ArticleServiceImpl(articleRepository);
    }

    @Test
    public void testSave() {
        Article article = new Article();
        article.setId(ARTICLE_ID);

        when(articleRepository.save(article)).thenReturn(article);

        Article articleSaved = articleService.save(article);
        assertEquals(ARTICLE_ID, articleSaved.getId());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    public void testFindByIdFound() {
        Article article = new Article();
        article.setId(ARTICLE_ID);

        when(articleRepository.findById(ARTICLE_ID)).thenReturn(Optional.of(article));

        Article articleFind = articleService.findById(ARTICLE_ID);
        assertEquals(ARTICLE_ID, articleFind.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(articleRepository.findById(ARTICLE_ID)).thenReturn(Optional.empty());

        Article article = articleService.findById(ARTICLE_ID);
        assertNull(article);
    }

    @Test
    public void testDeleteById() {
        articleService.delete(ARTICLE_ID);
        verify(articleRepository, times(1)).deleteById(ARTICLE_ID);
    }

    @Test
    public void testSearch() {
        Article article = new Article();
        article.setTitle(ARTICLE_TITLE);
        List<Article> articleList = new LinkedList<>();
        articleList.add(article);

        when(articleRepository.findTop10ByTitleContainingIgnoreCase(ARTICLE_TITLE)).thenReturn(articleList);
        List<Article> articlesFound = articleService.search(ARTICLE_TITLE);
        assertTrue(articlesFound.contains(article));
        verify(articleRepository, times(1)).findTop10ByTitleContainingIgnoreCase(ARTICLE_TITLE);
    }
}
