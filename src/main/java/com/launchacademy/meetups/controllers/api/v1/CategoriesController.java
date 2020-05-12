package com.launchacademy.meetups.controllers.api.v1;

import com.launchacademy.meetups.models.Category;
import com.launchacademy.meetups.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
  @Autowired
  private CategoryRepository categoryRepo;

  @GetMapping
  public Page<Category> getList(Pageable pageable) {
    return categoryRepo.findAll(pageable);
  }
}
