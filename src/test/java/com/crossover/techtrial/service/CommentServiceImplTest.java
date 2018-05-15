package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    private static final Long COMMENT_ID = 1L;

    @Mock
    private CommentRepository commentRepository;

    private CommentServiceImpl commentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        commentService = new CommentServiceImpl(commentRepository);
    }

    @Test
    public void testSave() {
        Comment comment = new Comment();
        comment.setId(COMMENT_ID);

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment commentSaved = commentService.save(comment);
        assertEquals(COMMENT_ID, commentSaved.getId());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void testFindAll() {
        Article article = Mockito.mock(Article.class);
        Comment comment = new Comment();
        comment.setArticle(article);
        List<Comment> comments = new LinkedList<>();
        comments.add(comment);

        when(article.getId()).thenReturn(1L);
        when(article.getDate()).thenReturn(LocalDateTime.now());
        when(commentRepository.findByArticle_IdOrderByDate(article.getId())).thenReturn(comments);

        List<Comment> commentsFound = commentService.findAll(article.getId());
        verify(commentRepository, times(1)).findByArticle_IdOrderByDate(article.getId());
        assertTrue(commentsFound.contains(comment));
    }
}
