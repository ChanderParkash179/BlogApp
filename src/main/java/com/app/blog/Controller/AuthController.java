package com.app.blog.Controller;

import com.app.blog.Service.UserService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/blog/user/auth/")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping("post/login")
    private ResponseEntity<?> login(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in AuthController.login() : {}");
        return new ResponseEntity<>(this.userService.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("post/register")
    private ResponseEntity<?> register(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in AuthController.register() : {}");
        return new ResponseEntity<>(this.userService.register(request), HttpStatus.OK);
    }

    @PostMapping("role/post/save")
    private ResponseEntity<?> addRole(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in AuthController.addRole() : {}");
        return new ResponseEntity<>(this.userService.saveRole(request), HttpStatus.CREATED);
    }

    @PostMapping("add/role/post/save")
    private ResponseEntity<?> addRoleToUser(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in AuthController.addRoleToUser() : {}");
        return new ResponseEntity<>(this.userService.addRoleToUser(request), HttpStatus.CREATED);
    }
}
