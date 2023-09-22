package com.app.blog.Repository;

import com.app.blog.Model.Category;
import com.app.blog.Model.Post;
import com.app.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    List<Post> findAllByOrderByIdAsc();
}
