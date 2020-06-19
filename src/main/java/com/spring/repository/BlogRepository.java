package com.spring.repository;

import com.spring.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
}
