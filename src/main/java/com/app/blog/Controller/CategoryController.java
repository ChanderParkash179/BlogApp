package com.app.blog.Controller;

import com.app.blog.Service.CategoryService;
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
@RequestMapping("api/blog/category/")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CategoryController.findById() : {}");
        return new ResponseEntity<>(this.categoryService.getById(request), HttpStatus.OK);
    }

    @PostMapping("get/title")
    private ResponseEntity<?> findByTitle(@Valid @RequestBody Map<String, Object> request) {
        try {
            logger.info("in CategoryController.findByTitle() : {}");
            return new ResponseEntity<>(this.categoryService.getByTitle(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findAll() {
        try {
            logger.info("in CategoryController.findAll() : {}");
            return new ResponseEntity<>(this.categoryService.list(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("post/save")
    private ResponseEntity<?> add(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CategoryController.add() : {}");
        return new ResponseEntity<>(this.categoryService.save(request), HttpStatus.CREATED);
    }

    @PostMapping("put/update")
    private ResponseEntity<?> update(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CategoryController.update() : {}");
        return new ResponseEntity<>(this.categoryService.update(request), HttpStatus.CREATED);
    }

    @PostMapping("delete/delete")
    private ResponseEntity<?> delete(@Valid @RequestBody Map<String, Object> request) {
        logger.info("in CategoryController.delete() : {}");
        return new ResponseEntity<>(this.categoryService.delete(request), HttpStatus.OK);
    }
}