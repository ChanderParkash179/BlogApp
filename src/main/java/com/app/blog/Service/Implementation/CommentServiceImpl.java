package com.app.blog.Service.Implementation;

import com.app.blog.Entity.Response;
import com.app.blog.Exception.ResourceNotFoundException;
import com.app.blog.Model.Comment;
import com.app.blog.Model.Post;
import com.app.blog.Model.User;
import com.app.blog.Repository.CommentRepository;
import com.app.blog.Repository.PostRepository;
import com.app.blog.Repository.UserRepository;
import com.app.blog.Service.CommentService;
import com.app.blog.Utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Response getById(Map<String, Object> input) {

        logger.info("in CommentServiceImpl.getById() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;

        Comment comment;

        if (id == null || id == 0) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        comment = this.commentRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.COMMENT, AppConstants.ID, id.toString()));

        if (comment == null) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        responseData.put("comment", comment);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_COMMENT_FOUND_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in CommentServiceImpl.getById() : {} - end");

        return response;
    }

    @Override
    public Response getByContent(Map<String, Object> input) {

        logger.info("in CommentServiceImpl.getByContent() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String content = input.get("content") != null ? (String) input.get("content") : null;

        Comment comment;

        try {
            if (content == null || content.isEmpty()) {
                responseData.put("comment", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_CONTENT_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            comment = this.commentRepository.findByContent(content);

            if (comment == null) {
                responseData.put("comment", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_COMMENT_NOT_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("comment", comment);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_COMMENT_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in CommentServiceImpl.getByTitle() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in CommentServiceImpl.getByTitle() : {} - end");

        return response;
    }

    @Override
    public Response getByPost(Map<String, Object> input) {

        logger.info("in CommentServiceImpl.getByPost() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer postId = input.get("postId") != null ? (Integer) input.get("postId") : 0;

        List<Comment> comments;
        Post post;

        try {
            if (postId == 0) {
                responseData.put("comment", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            post = this.postRepository.findById(postId.longValue()).orElseThrow(
                    () -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID, postId.toString()));

            comments = this.commentRepository.findByPost(post);

            if (comments == null) {
                responseData.put("comments", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_COMMENT_NOT_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("comments", comments);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_COMMENT_FOUND_SUCCESSFULLY_AGAINST_POST);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in CommentServiceImpl.getByPost() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in CommentServiceImpl.getByPost() : {} - end");

        return response;
    }

    @Override
    public Response getByUser(Map<String, Object> input) {

        logger.info("in CommentServiceImpl.getByUser() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer userId = input.get("userId") != null ? (Integer) input.get("userId") : 0;

        List<Comment> comments;
        User user;

        if (userId == 0) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        user = this.userRepository.findById(userId.longValue()).orElseThrow(
                () -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, userId.toString()));

        comments = this.commentRepository.findByUser(user);

        if (comments == null) {
            responseData.put("comments", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_USER_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        responseData.put("comments", comments);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_COMMENT_FOUND_SUCCESSFULLY_AGAINST_USER);
        response.setResponseData(responseData);

        logger.info("in CommentServiceImpl.getByUser() : {} - end");

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in CommentServiceImpl.save() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String content = input.get("content") != null ? (String) input.get("content") : null;

        Integer userId = (Integer) input.get("userId") != 0 ? (Integer) input.get("userId") : 0;
        Integer postId = (Integer) input.get("postId") != 0 ? (Integer) input.get("postId") : 0;

        Post post;
        Comment comment;
        User user;

        if (content == null || content.isEmpty()) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_CONTENT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (postId == 0) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_POST_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (userId == 0) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_USER_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        } else {

            Comment alreadyAvailableComment = this.commentRepository.findByContent(content);

            if (alreadyAvailableComment != null) {
                responseData.put("comment", alreadyAvailableComment);
                response.setResponseCode(AppConstants.FOUND);
                response.setResponseMessage(AppConstants.MSG_POST_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            post = this.postRepository.findById(postId.longValue())
                    .orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID,
                            postId.toString()));

            user = this.userRepository.findById(userId.longValue())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, userId.toString()));

            comment = new Comment(content, post, user);

            this.commentRepository.save(comment);

            responseData.put("comment", comment);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_COMMENT_SAVED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in CommentServiceImpl.save() : {} - end");

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in CommentServiceImpl.update() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String content = input.get("content") != null ? (String) input.get("content") : null;

        Integer userId = (Integer) input.get("userId") != 0 ? (Integer) input.get("userId") : 0;
        Integer postId = (Integer) input.get("postId") != 0 ? (Integer) input.get("postId") : 0;

        Post post;
        Comment comment;
        User user;

        if (id == 0) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (content == null || content.isEmpty()) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_CONTENT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (postId == 0) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_POST_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (userId == 0) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_USER_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        post = this.postRepository.findById(postId.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID, id.toString()));

        user = this.userRepository.findById(userId.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, id.toString()));

        if (post == null) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_POST_NOT_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        } else if (user == null) {
            responseData.put("comment", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_USER_NOT_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        } else {

            comment = this.commentRepository.findById(id.longValue()).orElseThrow(
                    () -> new ResourceNotFoundException(AppConstants.COMMENT, AppConstants.ID, id.toString()));

            comment.setContent(content);
            comment.setPost(post);
            comment.setUser(user);

            this.commentRepository.save(comment);

            responseData.put("comment", comment);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_COMMENT_UPDATED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in CommentServiceImpl.update() : {} - end");

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in CommentServiceImpl.delete() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;

        Comment comment = null;

        if (id == 0) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        comment = this.commentRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.COMMENT, AppConstants.ID, id.toString()));

        if (comment.getId() == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        this.commentRepository.deleteById(id.longValue());

        responseData.put("comment", comment);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_COMMENT_DELETED_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in CommentServiceImpl.delete() : {} - end");

        return response;
    }

}
