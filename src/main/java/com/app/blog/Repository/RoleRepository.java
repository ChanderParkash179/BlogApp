package com.app.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}