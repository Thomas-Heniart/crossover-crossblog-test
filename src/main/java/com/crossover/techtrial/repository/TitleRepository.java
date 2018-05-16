package com.crossover.techtrial.repository;

import com.crossover.techtrial.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TitleRepository extends JpaRepository<Title, String> {
}
