package com.launchacademy.meetups.repositories;

import com.launchacademy.meetups.models.Category;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
  List<Category> findByName(String name);
}
