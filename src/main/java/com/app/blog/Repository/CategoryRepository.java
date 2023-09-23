package com.app.blog.Repository;

import com.app.blog.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);

    List<Category> findAllByOrderByIdAsc();
}