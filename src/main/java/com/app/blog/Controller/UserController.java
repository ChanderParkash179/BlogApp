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
@RequestMapping("api/blog/")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in UserController.findById() : {}");
        return new ResponseEntity<>(this.userService.getById(request), HttpStatus.OK);
    }

    @PostMapping("get/name")
    private ResponseEntity<?> findByName(@Valid @RequestBody Map<String, Object> request) {
        try {
            logger.info("in UserController.findByName() : {}");
            return new ResponseEntity<>(this.userService.getByName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/email")
    private ResponseEntity<?> findByEmail(@Valid @RequestBody Map<String, Object> request) {
        try {
            logger.info("in UserController.findByName() : {}");
            return new ResponseEntity<>(this.userService.getByEmail(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findCities() {
        try {
            logger.info("in UserController.findUsers() : {}");
            return new ResponseEntity<>(this.userService.list(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("post/save")
    private ResponseEntity<?> addUser(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in UserController.addUser() : {}");
        return new ResponseEntity<>(this.userService.save(request), HttpStatus.CREATED);
    }

    @PostMapping("put/update")
    private ResponseEntity<?> updateUser(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in UserController.updateUser() : {}");
        return new ResponseEntity<>(this.userService.update(request), HttpStatus.CREATED);
    }

    @PostMapping("delete/delete")
    private ResponseEntity<?> deleteUser(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in UserController.deleteUser() : {}");
        return new ResponseEntity<>(this.userService.delete(request), HttpStatus.OK);
    }
}