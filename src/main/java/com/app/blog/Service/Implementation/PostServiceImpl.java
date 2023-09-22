package com.app.blog.Service.Implementation;

import com.app.blog.Entity.Response;
import com.app.blog.Exception.ResourceNotFoundException;
import com.app.blog.Model.Category;
import com.app.blog.Model.Post;
import com.app.blog.Model.User;
import com.app.blog.Repository.CategoryRepository;
import com.app.blog.Repository.PostRepository;
import com.app.blog.Repository.UserRepository;
import com.app.blog.Service.PostService;
import com.app.blog.Utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in PostServiceImpl.getById() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;

        Post post;

        if (id == null || id == 0) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        post = this.postRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID, id.toString()));

        if (post == null) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        responseData.put("post", post);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_POST_FOUND_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in PostServiceImpl.getById() : {} - end");

        return response;
    }

    @Override
    public Response getByTitle(Map<String, Object> input) {

        logger.info("in PostServiceImpl.getByTitle() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String title = input.get("title") != null ? (String) input.get("title") : null;

        Post post;

        try {
            if (title == null || title.isEmpty()) {
                responseData.put("post", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_TITLE_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            post = this.postRepository.findByTitle(title);

            if (post == null) {
                responseData.put("post", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_TITLE_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("post", post);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_POST_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in PostServiceImpl.getByTitle() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in PostServiceImpl.getByTitle() : {} - end");

        return response;
    }

    @Override
    public Response getByCategory(Map<String, Object> input) {

        logger.info("in PostServiceImpl.getByCategory() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer categoryId = input.get("categoryId") != null ? (Integer) input.get("categoryId") : null;

        List<Post> posts;
        Category category;

        try {
            if (categoryId == 0) {
                responseData.put("post", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_CATEGORY_ID_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            category = this.categoryRepository.findById(categoryId.longValue()).orElseThrow(
                    () -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.ID, categoryId.toString()));

            posts = this.postRepository.findByCategory(category);

            if (posts == null) {
                responseData.put("posts", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_CATEGORY_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("posts", posts);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_POST_FOUND_SUCCESSFULLY_AGAINST_CATEGORY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in PostServiceImpl.getByCategory() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in PostServiceImpl.getByCategory() : {} - end");

        return response;
    }

    @Override
    public Response getByUser(Map<String, Object> input) {

        logger.info("in PostServiceImpl.getByUser() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer userId = input.get("userId") != null ? (Integer) input.get("userId") : null;

        List<Post> posts;
        User user;

        try {
            if (userId == 0) {
                responseData.put("post", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_USER_ID_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            user = this.userRepository.findById(userId.longValue()).orElseThrow(
                    () -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, userId.toString()));

            posts = this.postRepository.findByUser(user);

            if (posts == null) {
                responseData.put("posts", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_USER_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("posts", posts);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_POST_FOUND_SUCCESSFULLY_AGAINST_USER);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in PostServiceImpl.getByUser() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in PostServiceImpl.getByUser() : {} - end");

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in PostServiceImpl.save() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String title = input.get("title") != null ? (String) input.get("title") : null;
        String content = input.get("content") != null ? (String) input.get("content") : null;
        String imageUrl = input.get("imageUrl") != null ? (String) input.get("imageUrl") : null;
        String addedDate = sdf.format(new Date());

        Integer userId = (Integer) input.get("userId") != 0 ? (Integer) input.get("userId") : 0;
        Integer categoryId = (Integer) input.get("categoryId") != 0 ? (Integer) input.get("categoryId") : 0;

        Post post;
        Category category;
        User user;

        if (title == null || title.isEmpty()) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_TITLE_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (content == null || content.isEmpty()) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_CONTENT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_IMAGE_URL_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (categoryId == 0) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_CATEGORY_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (userId == 0) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_USER_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        } else {

            Post alreadyPostAvailable = this.postRepository.findByTitle(title);

            if (alreadyPostAvailable != null) {
                responseData.put("Post", alreadyPostAvailable);
                response.setResponseCode(AppConstants.FOUND);
                response.setResponseMessage(AppConstants.MSG_POST_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            category = this.categoryRepository.findById(categoryId.longValue())
                    .orElseThrow(() -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.ID,
                            categoryId.toString()));

            user = this.userRepository.findById(userId.longValue())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, userId.toString()));

            post = new Post(title, content, imageUrl, addedDate, category, user);

            this.postRepository.save(post);

            responseData.put("post", post);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_POST_SAVED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in PostServiceImpl.save() : {} - end");

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in PostServiceImpl.update() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String title = input.get("title") != null ? (String) input.get("title") : null;
        String content = input.get("content") != null ? (String) input.get("content") : null;
        String imageUrl = input.get("imageUrl") != null ? (String) input.get("imageUrl") : null;
        String addedDate = sdf.format(new Date());

        Integer userId = (Integer) input.get("userId") != 0 ? (Integer) input.get("userId") : 0;
        Integer categoryId = (Integer) input.get("categoryId") != 0 ? (Integer) input.get("categoryId") : 0;

        Post post;
        Category category;
        User user;

        if (id == 0) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (title == null || title.isEmpty()) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_TITLE_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (content == null || content.isEmpty()) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_CONTENT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_IMAGE_URL_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (categoryId == 0) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_CATEGORY_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (userId == 0) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_USER_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        post = this.postRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID, id.toString()));

        if (post == null) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_POST_NOT_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        } else {

            category = this.categoryRepository.findById(categoryId.longValue())
                    .orElseThrow(() -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.ID,
                            categoryId.toString()));

            user = this.userRepository.findById(userId.longValue())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, userId.toString()));

            post = this.postRepository.findById(id.longValue())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID, id.toString()));

            post.setTitle(title);
            post.setContent(content);
            post.setImageUrl(imageUrl);
            post.setAddedDate(addedDate);
            post.setCategory(category);
            post.setUser(user);

            this.postRepository.save(post);

            responseData.put("post", post);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_POST_UPDATED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in PostServiceImpl.update() : {} - end");

        return response;
    }

    @Override
    public Response list() {
        logger.info("in PostServiceImpl.list() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<Post> posts = this.postRepository.findAllByOrderByIdAsc();

            if (posts == null) {
                responseData.put("posts", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_POST_NOT_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("posts", posts);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_POST_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in PostServiceImpl.list() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in PostServiceImpl.list() : {} - end");

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in PostServiceImpl.delete() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;

        Post post;

        if (id == 0) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        post = this.postRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID, id.toString()));

        if (post.getId() == null) {
            responseData.put("post", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        this.postRepository.deleteById(id.longValue());

        responseData.put("post", post);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_POST_DELETED_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in PostServiceImpl.delete() : {} - end");

        return response;
    }

    @Override
    public Response search(Map<String, Object> input) {
        return null;
    }
}
