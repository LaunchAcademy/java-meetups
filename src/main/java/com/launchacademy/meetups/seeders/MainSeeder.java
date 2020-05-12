package com.launchacademy.meetups.seeders;

import com.launchacademy.meetups.models.Category;
import com.launchacademy.meetups.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

  @Autowired private CategoryRepository categoryRepo;

  @Override
  public void run(String... args) throws Exception {
    String[] categoryNames = {"Entertainment", "Education", "Tourism"};

    for(String name : categoryNames) {
      if(categoryRepo.findByName(name).size() == 0) {
        Category category = new Category();
        category.setName(name);
        categoryRepo.save(category);
      }
    }
  }
}
