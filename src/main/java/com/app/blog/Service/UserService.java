package com.app.blog.Service;

import com.app.blog.Entity.Response;

import java.util.*;

public interface UserService {

    Response getById(Map<String, Object> input);

    Response getByFirstName(Map<String, Object> input);
    Response getByLastName(Map<String, Object> input);

    Response getByEmail(Map<String, Object> input);

    Response save(Map<String, Object> input);

    Response update(Map<String, Object> input);

    Response list();

    Response delete(Map<String, Object> input);
}