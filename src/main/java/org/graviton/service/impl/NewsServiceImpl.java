package org.graviton.service.impl;

import org.graviton.model.News;
import org.graviton.repository.NewsRepository;
import org.graviton.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Cacheable("news")
    public List<News> findAll() {
        return newsRepository.findAll().stream().sorted(Comparator.comparingInt(News::getId).reversed()).collect(Collectors.toList());
    }
}
