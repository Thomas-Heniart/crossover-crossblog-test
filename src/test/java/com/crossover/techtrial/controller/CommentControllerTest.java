package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.service.ArticleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

    private static final Long ARTICLE_ID = 1L;

    @Autowired
    private TestRestTemplate template;

    @Mock
    private ArticleService articleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateComment() {
        Article article = new Article();
        article.setId(ARTICLE_ID);

        when(articleService.findById(ARTICLE_ID)).thenReturn(article);

        HttpEntity<Object> comment = getHttpEntity("{\"email\": \"user1@gmail.com\", \"message\": \"hello\" }");
        ResponseEntity<Comment> resultAsset = template.postForEntity("/articles/" + ARTICLE_ID + "/comments", comment, Comment.class);
        Assert.assertNotNull(resultAsset.getBody().getId());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }
}
