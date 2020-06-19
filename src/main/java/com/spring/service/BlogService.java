package com.spring.service;

import com.spring.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface BlogService {
    Page<Blog> findAll(Pageable pageable);

    Optional<Blog> findById(Long id);

    void save(Blog blog);

    void remove(Long id);
}
