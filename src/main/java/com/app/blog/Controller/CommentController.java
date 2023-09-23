package com.app.blog.Controller;

import com.app.blog.Service.CommentService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/blog/comment/")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CommentController.findById() : {}");
        return new ResponseEntity<>(this.commentService.getById(request), HttpStatus.OK);
    }

    @PostMapping("get/content")
    private ResponseEntity<?> findByContent(@Valid @RequestBody Map<String, Object> request) {
        try {
            logger.info("in CommentController.findByContent() : {}");
            return new ResponseEntity<>(this.commentService.getByContent(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/user")
    private ResponseEntity<?> findByUser(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CommentController.findByUser() : {}");
        return new ResponseEntity<>(this.commentService.getByUser(request), HttpStatus.OK);
    }

    @PostMapping("get/post")
    private ResponseEntity<?> findByPost(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CommentController.findByPost() : {}");
        return new ResponseEntity<>(this.commentService.getByPost(request), HttpStatus.OK);
    }

    @PostMapping("post/save")
    private ResponseEntity<?> add(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CommentController.add() : {}");
        return new ResponseEntity<>(this.commentService.save(request), HttpStatus.CREATED);
    }

    @PostMapping("put/update")
    private ResponseEntity<?> update(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CommentController.update() : {}");
        return new ResponseEntity<>(this.commentService.update(request), HttpStatus.CREATED);
    }

    @PostMapping("delete/delete")
    private ResponseEntity<?> delete(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CommentController.delete() : {}");
        return new ResponseEntity<>(this.commentService.delete(request), HttpStatus.OK);
    }
}