package com.app.blog.Service;

import com.app.blog.Entity.Response;

import java.util.Map;

public interface CommentService {

    Response getById(Map<String, Object> input);

    Response getByContent(Map<String, Object> input);

    Response getByPost(Map<String, Object> input);

    Response getByUser(Map<String, Object> input);

    Response save(Map<String, Object> input);

    Response update(Map<String, Object> input);

    Response delete(Map<String, Object> input);
}