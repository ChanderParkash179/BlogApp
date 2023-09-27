package com.app.blog.Repository;

import com.app.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    List<User> findAllByOrderByIdAsc();
}
