package com.app.blog.Service;

import com.app.blog.Entity.Response;

import java.util.Map;

public interface PostService {

    Response getById(Map<String, Object> input);

    Response getByTitle(Map<String, Object> input);

    Response getByCategory(Map<String, Object> input);

    Response getByUser(Map<String, Object> input);

    Response save(Map<String, Object> input);

    Response update(Map<String, Object> input);

    Response list();

    Response pageableList(Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    Response delete(Map<String, Object> input);

    Response search(Map<String, Object> input);

}