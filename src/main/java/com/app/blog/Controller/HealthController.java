package com.app.blog.Controller;

import com.app.blog.Utils.LoggerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/actuator/")
public class HealthController {

  private static final Logger logger = LoggerFactory.getLogger(
    HealthController.class
  );

  @GetMapping("health")
  public ResponseEntity<?> health() {
    try {
      logger.info("in CategoryServiceImpl.getByTitle() : {} - start");
      return new ResponseEntity<Void>(HttpStatus.OK);
    } catch (Exception ex) {
      logger.info("in CategoryServiceImpl.getByTitle() : {} - error");
      return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
    }
  }
}