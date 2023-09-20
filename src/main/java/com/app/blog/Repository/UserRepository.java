package com.app.blog.Repository;

import com.app.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByName(String name);

    List<User> findAllByOrderByIdAsc();
}
