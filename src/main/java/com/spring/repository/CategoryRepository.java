package com.spring.repository;

import com.spring.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends PagingAndSortingRepository <Category, Long> {

}
