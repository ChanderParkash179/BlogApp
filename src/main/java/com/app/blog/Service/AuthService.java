package com.app.blog.Service;

import java.util.Map;

import com.app.blog.Entity.Response;

public interface AuthService {

    Response register(Map<String, Object> input);

    Response authenticate(Map<String, Object> input);

    Response saveRole(Map<String, Object> input);

    Response addRoleToUser(Map<String, Object> input);
}
