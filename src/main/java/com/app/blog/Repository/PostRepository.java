package com.app.blog.Repository;

import com.app.blog.Model.Category;
import com.app.blog.Model.Post;
import com.app.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    List<Post> findAllByOrderByIdAsc();

    @Query(value = "SELECT * FROM POST WHERE POST_TITLE LIKE :keyword", nativeQuery = true)
    List<Post> search(@Param("keyword") String title);
}
