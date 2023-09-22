package com.app.blog.Service.Implementation;

import com.app.blog.Entity.Response;
import com.app.blog.Exception.ResourceNotFoundException;
import com.app.blog.Model.Category;
import com.app.blog.Repository.CategoryRepository;
import com.app.blog.Service.CategoryService;
import com.app.blog.Utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in CategoryServiceImpl.getById() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;

        Category category = null;

        if (id == null || id == 0) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        category = this.categoryRepository.findById(id.longValue()).orElseThrow(
                () -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.ID, id.toString()));

        if (category == null) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        responseData.put("category", category);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_CATEGORY_FOUND_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in CategoryServiceImpl.getById() : {} - end");

        return response;
    }

    @Override
    public Response getByTitle(Map<String, Object> input) {

        logger.info("in CategoryServiceImpl.getByTitle() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String title = (String) input.get("title") != null ? (String) input.get("title") : null;

        Category category = null;

        try {
            if (title == null || title.isEmpty()) {
                responseData.put("category", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_TITLE_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            category = this.categoryRepository.findByTitle(title);

            if (category == null) {
                responseData.put("category", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_TITLE_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("category", category);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_CATEGORY_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in CategoryServiceImpl.getByName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in CategoryServiceImpl.getByName() : {} - end");

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in CategoryServiceImpl.save() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String title = (String) input.get("title") != null ? (String) input.get("title") : null;
        String description = (String) input.get("description") != null ? (String) input.get("description") : null;

        Category category = null;

        if (title == null || title.isEmpty()) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.BAD_REQUEST);
            response.setResponseMessage(AppConstants.MSG_NO_TITLE_PROVIDED);
            response.setResponseData(responseData);
            return response;
        } else {

            Category alreadyCategoryAvailable = this.categoryRepository.findByTitle(title);

            if (alreadyCategoryAvailable != null) {
                responseData.put("Category", alreadyCategoryAvailable);
                response.setResponseCode(AppConstants.FOUND);
                response.setResponseMessage(AppConstants.MSG_CATEGORY_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            category = new Category(title, description);

            this.categoryRepository.save(category);

            responseData.put("category", category);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_CATEGORY_SAVED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in CategoryServiceImpl.save() : {} - end");

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in CategoryServiceImpl.update() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String title = (String) input.get("title") != null ? (String) input.get("title") : null;
        String description = (String) input.get("description") != null ? (String) input.get("description") : null;

        Category category = null;

        if (id == 0) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (title == null || title.isEmpty()) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_TITLE_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (description == null || description.isEmpty()) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_DESCRIPTION_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        category = this.categoryRepository.findById(id.longValue()).orElseThrow(
                () -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.ID, id.toString()));

        if (category == null) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_CATEGORY_NOT_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        } else {
            Category titleAvailable = this.categoryRepository.findByTitle(title) != null
                    ? this.categoryRepository.findByTitle(title)
                    : null;

            if (titleAvailable == null) {
                category.setTitle(title);
                category.setDescription(description);

                this.categoryRepository.save(category);

                responseData.put("category", category);
                response.setResponseCode(AppConstants.CREATED);
                response.setResponseMessage(AppConstants.MSG_CATEGORY_UPDATED_SUCCESSFULLY);
                response.setResponseData(responseData);
            } else {
                responseData.put("category", titleAvailable);
                response.setResponseCode(AppConstants.INTERNAL_SERVER_ERROR);
                response.setResponseMessage(AppConstants.MSG_USER_TITLE_ALREADY_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }
        }

        logger.info("in CategoryServiceImpl.update() : {} - end");

        return response;
    }

    @Override
    public Response list() {
        logger.info("in CategoryServiceImpl.list() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<Category> categories = this.categoryRepository.findAllByOrderByIdAsc();

            if (categories == null) {
                responseData.put("categories", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_CATEGORY_NOT_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("categories", categories);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_CATEGORY_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in CategoryServiceImpl.list() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in CategoryServiceImpl.list() : {} - end");

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in CategoryServiceImpl.delete() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;

        Category category = null;

        if (id == 0) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        category = this.categoryRepository.findById(id.longValue()).orElseThrow(
                () -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.ID, id.toString()));

        if (category.getId() == null) {
            responseData.put("category", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        this.categoryRepository.deleteById(id.longValue());

        responseData.put("category", category);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_CATEGORY_DELETED_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in CategoryServiceImpl.delete() : {} - end");

        return response;
    }
}
