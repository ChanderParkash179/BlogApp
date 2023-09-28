package com.app.blog.Service.Implementation;

import com.app.blog.Entity.Response;
import com.app.blog.Exception.ResourceNotFoundException;
import com.app.blog.Model.User;
import com.app.blog.Repository.UserRepository;
import com.app.blog.Service.UserService;
import com.app.blog.Utils.AppConstants;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public Response register(Map<String, Object> input) {

        logger.info("in UserServiceImpl.register() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String email = input.get("email") != null ? (String) input.get("email") : null;
        String firstName = input.get("firstName") != null ? (String) input.get("firstName") : null;
        String lastName = input.get("lastName") != null ? (String) input.get("lastName") : null;
        String password = input.get("password") != null ? (String) input.get("password") : null;
        String about = input.get("about") != null ? (String) input.get("about") : null;
        String role = "API_USER";

        User user = null;

        if (firstName == null || firstName.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_FIRST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (lastName == null || lastName.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_LAST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (email == null || email.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_EMAIL_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (password == null || password.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_PASSWORD_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (about == null || about.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ABOUT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        } else {

            User alreadyUserAvailable = this.userRepository.findByEmail(email);

            if (alreadyUserAvailable != null) {
                responseData.put("User", alreadyUserAvailable);
                response.setResponseCode(AppConstants.FOUND);
                response.setResponseMessage(AppConstants.MSG_EMAIL_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            user = new User(firstName, lastName, email, password, about, role);

            this.userRepository.save(user);

            responseData.put("user", user);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_USER_SAVED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in UserServiceImpl.register() : {} - end");

        return response;
    }

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in UserServiceImpl.getById() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;

        User user = null;

        if (id == null || id == 0) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        user = this.userRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, id.toString()));

        if (user == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        responseData.put("user", user);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_USER_FOUND_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in UserServiceImpl.getById() : {} - end");

        return response;
    }

    @Override
    public Response getByFirstName(Map<String, Object> input) {

        logger.info("in UserServiceImpl.getByFirstName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String firstName = input.get("firstName") != null ? (String) input.get("firstName") : null;

        User user = null;

        try {
            if (firstName == null || firstName.isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_FIRST_NAME_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            user = this.userRepository.findByFirstName(firstName);

            if (user == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_FIRST_NAME_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("user", user);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_USER_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in UserServiceImpl.getByFirstName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in UserServiceImpl.getByFirstName() : {} - end");

        return response;
    }

    @Override
    public Response getByLastName(Map<String, Object> input) {

        logger.info("in UserServiceImpl.getByLastName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String lastName = input.get("lastName") != null ? (String) input.get("lastName") : null;

        User user = null;

        try {
            if (lastName == null || lastName.isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_LAST_NAME_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            user = this.userRepository.findByLastName(lastName);

            if (user == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_LAST_NAME_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("user", user);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_USER_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in UserServiceImpl.getByLastName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in UserServiceImpl.getByLastName() : {} - end");

        return response;
    }

    @Override
    public Response getByEmail(Map<String, Object> input) {

        logger.info("in UserServiceImpl.getByEmail() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String email = input.get("email") != null ? (String) input.get("email") : null;

        User user = null;

        try {
            if (email == null || email.isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_EMAIL_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }
            user = this.userRepository.findByEmail(email);

            if (user == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_EMAIL_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("user", user);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_USER_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in UserServiceImpl.getByEmail() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in UserServiceImpl.getByEmail() : {} - end");

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in UserServiceImpl.save() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String email = input.get("email") != null ? (String) input.get("email") : null;
        String firstName = input.get("firstName") != null ? (String) input.get("firstName") : null;
        String lastName = input.get("lastName") != null ? (String) input.get("lastName") : null;
        String password = input.get("password") != null ? (String) input.get("password") : null;
        String about = input.get("about") != null ? (String) input.get("about") : null;
        String role = input.get("role") != null ? (String) input.get("role") : "LOCAL_USER";

        User user = null;

        if (firstName == null || firstName.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_FIRST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (lastName == null || lastName.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_LAST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (email == null || email.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_EMAIL_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (password == null || password.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_PASSWORD_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (about == null || about.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ABOUT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        } else {

            User alreadyUserAvailable = this.userRepository.findByEmail(email);

            if (alreadyUserAvailable != null) {
                responseData.put("User", alreadyUserAvailable);
                response.setResponseCode(AppConstants.FOUND);
                response.setResponseMessage(AppConstants.MSG_EMAIL_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            user = new User(firstName, lastName, email, password, about, role);

            this.userRepository.save(user);

            responseData.put("user", user);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_USER_SAVED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in UserServiceImpl.save() : {} - end");

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in UserServiceImpl.update() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String email = input.get("email") != null ? (String) input.get("email") : null;
        String firstName = input.get("firstName") != null ? (String) input.get("firstName") : null;
        String lastName = input.get("lastName") != null ? (String) input.get("lastName") : null;
        String password = input.get("password") != null ? (String) input.get("password") : null;
        String about = input.get("about") != null ? (String) input.get("about") : null;
        String role = input.get("role") != null ? (String) input.get("role") : "LOCAL_USER";

        User user;

        if (id == 0) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (firstName == null || firstName.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_FIRST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (lastName == null || lastName.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_LAST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (email == null || email.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_EMAIL_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (password == null || password.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_PASSWORD_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (about == null || about.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ABOUT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        user = this.userRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, id.toString()));

        if (user == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_USER_NOT_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        } else {
            User emailAvailable = this.userRepository.findByEmail(email) != null
                    ? this.userRepository.findByEmail(email)
                    : null;

            if (emailAvailable == null) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setAbout(about);
                user.setRole(role);

                this.userRepository.save(user);

                responseData.put("user", user);
                response.setResponseCode(AppConstants.CREATED);
                response.setResponseMessage(AppConstants.MSG_USER_UPDATED_SUCCESSFULLY);
                response.setResponseData(responseData);
            } else {
                responseData.put("user", emailAvailable);
                response.setResponseCode(AppConstants.INTERNAL_SERVER_ERROR);
                response.setResponseMessage(AppConstants.MSG_USER_EMAIL_ALREADY_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }
        }

        logger.info("in UserServiceImpl.update() : {} - end");

        return response;
    }

    @Override
    public Response list() {
        logger.info("in UserServiceImpl.list() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<User> users = this.userRepository.findAllByOrderByIdAsc();

            if (users == null) {
                responseData.put("users", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_USER_NOT_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }

            responseData.put("users", users);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_USER_FOUND_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in UserServiceImpl.list() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in UserServiceImpl.list() : {} - end");

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in UserServiceImpl.delete() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;

        User user = null;

        if (id == 0) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        user = this.userRepository.findById(id.longValue())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER, AppConstants.ID, id.toString()));

        if (user.getId() == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ID_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        this.userRepository.deleteById(id.longValue());

        responseData.put("user", user);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_USER_DELETED_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in UserServiceImpl.delete() : {} - end");

        return response;
    }
}
