package com.crossover.techtrial.repository;

import com.crossover.techtrial.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    List<Article> findAllByTitleIdContainingIgnoreCase(String title);

    List<Article> findTop10ByTitleIdContainingIgnoreCase(String title);

}
