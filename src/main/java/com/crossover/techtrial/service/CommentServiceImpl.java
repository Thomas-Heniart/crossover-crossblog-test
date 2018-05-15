package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /*
     * Returns all the Comments related to article along with Pagination
     * information.
     */
    public List<Comment> findAll(Long articleId) {
        return commentRepository.findByArticle_IdOrderByDate(articleId);
    }

    /*
     * Save the default article.
     */
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

}
