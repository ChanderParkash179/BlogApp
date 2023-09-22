package com.app.blog.Controller;

import com.app.blog.Service.PostService;
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
@RequestMapping("api/blog/post/")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in PostController.findById() : {}");
        return new ResponseEntity<>(this.postService.getById(request), HttpStatus.OK);
    }

    @PostMapping("get/title")
    private ResponseEntity<?> findByTitle(@Valid @RequestBody Map<String, Object> request) {
        try {
            logger.info("in PostController.findByTitle() : {}");
            return new ResponseEntity<>(this.postService.getByTitle(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/category")
    private ResponseEntity<?> findByCategory(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in PostController.findByCategory() : {}");
        return new ResponseEntity<>(this.postService.getByCategory(request), HttpStatus.OK);
    }

    @PostMapping("get/user")
    private ResponseEntity<?> findByUser(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in PostController.findByUser() : {}");
        return new ResponseEntity<>(this.postService.getByUser(request), HttpStatus.OK);
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findAll() {
        try {
            logger.info("in PostController.findAll() : {}");
            return new ResponseEntity<>(this.postService.list(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("post/save")
    private ResponseEntity<?> add(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in PostController.add() : {}");
        return new ResponseEntity<>(this.postService.save(request), HttpStatus.CREATED);
    }

    @PostMapping("put/update")
    private ResponseEntity<?> update(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in PostController.update() : {}");
        return new ResponseEntity<>(this.postService.update(request), HttpStatus.CREATED);
    }

    @PostMapping("delete/delete")
    private ResponseEntity<?> delete(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in PostController.delete() : {}");
        return new ResponseEntity<>(this.postService.delete(request), HttpStatus.OK);
    }
}