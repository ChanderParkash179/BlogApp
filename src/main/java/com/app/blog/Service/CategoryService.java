package com.app.blog.Service;

import com.app.blog.Entity.Response;

import java.util.Map;

public interface CategoryService {

    Response getById(Map<String, Object> input);

    Response getByTitle(Map<String, Object> input);

    Response save(Map<String, Object> input);

    Response update(Map<String, Object> input);

    Response list();

    Response delete(Map<String, Object> input);
}