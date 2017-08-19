package org.graviton.repository;

import org.graviton.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAll();
}
