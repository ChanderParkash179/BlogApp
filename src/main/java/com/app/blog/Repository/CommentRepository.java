package com.app.blog.Repository;

import com.app.blog.Model.Comment;
import com.app.blog.Model.Post;
import com.app.blog.Model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByContent(String content);

    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
