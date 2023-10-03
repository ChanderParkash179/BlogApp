package com.app.blog.Service.Implementation;

import com.app.blog.Entity.Response;
import com.app.blog.Model.Role;
import com.app.blog.Model.User;
import com.app.blog.Repository.RoleRepository;
import com.app.blog.Repository.UserRepository;
import com.app.blog.Security.JWT.JWTTokenHelper;
import com.app.blog.Service.AuthService;
import com.app.blog.Utils.AppConstants;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final JWTTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Response register(Map<String, Object> input) {

        logger.info("in AuthServiceImpl.register() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String email = input.get("email") != null ? (String) input.get("email") : null;
        String firstName = input.get("firstName") != null ? (String) input.get("firstName") : null;
        String lastName = input.get("lastName") != null ? (String) input.get("lastName") : null;
        String password = input.get("password") != null ? (String) input.get("password") : null;
        String about = input.get("about") != null ? (String) input.get("about") : null;
        String source = input.get("source") != null ? (String) input.get("source") : "API";
        List<String> roles = input.get("roles") != null ? (List<String>) input.get("roles") : new ArrayList<>();

        User user;

        if (firstName == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_FIRST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (lastName == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_LAST_NAME_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (email == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_EMAIL_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (password == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_PASSWORD_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (about == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ABOUT_PROVIDED);
            response.setResponseData(responseData);
            return response;
        } else {

            User availableEmail = this.userRepository.findByEmail(email);

            if (availableEmail != null) {
                responseData.put("user", availableEmail);
                response.setResponseCode(AppConstants.FOUND);
                response.setResponseMessage(AppConstants.MSG_EMAIL_AVAILABLE);
                response.setResponseData(responseData);
                return response;
            }
            Set<Role> userRoles = new HashSet<>();

            for (String role : roles) {
                userRoles.add(this.roleRepository.findByName(role));
            }

            logger.info("USER ROLES => " + userRoles);
            user = new User(firstName, lastName, email, passwordEncoder.encode(password), about, source, userRoles);

            this.userRepository.save(user);

            responseData.put("user", user);
            response.setResponseCode(AppConstants.CREATED);
            response.setResponseMessage(AppConstants.MSG_USER_REGISTERED_SUCCESSFULLY);
            response.setResponseData(responseData);
        }

        logger.info("in AuthServiceImpl.register() : {} - end");

        return response;
    }

    @Override
    public Response authenticate(Map<String, Object> input) {

        logger.info("in AuthServiceImpl.authenticate() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String username = input.get("username") != null ? (String) input.get("username") : null;
        String password = input.get("password") != null ? (String) input.get("password") : null;

        try {
            if (username == null || username.isEmpty()) {
                responseData.put("jwtResponse", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_USERNAME_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            if (password == null || password.isEmpty()) {
                responseData.put("jwtResponse", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_PASSWORD_PROVIDED);
                response.setResponseData(responseData);
                return response;
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    password);

            this.authenticationManager.authenticate(authenticationToken);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            String token = this.jwtTokenHelper.generateToken(userDetails);

            Map<String, Object> jwtAuthResponse = new HashMap<>();
            jwtAuthResponse.put("token", token);

            responseData.put("jwtResponse", jwtAuthResponse);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_TOKEN_GENERATED_SUCCESSFULLY);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            logger.error("in AuthServiceImpl.authenticate() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in AuthServiceImpl.authenticate() : {} - end");

        return response;
    }

    @Override
    public Response saveRole(Map<String, Object> input) {
        logger.info("in UserServiceImpl.saveRole() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String roles = input.get("role") != null ? (String) input.get("role") : null;

        Role role;

        if (roles == null || roles.isEmpty()) {
            responseData.put("role", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ROLE_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        Role alreadyAvailableRole = this.roleRepository.findByName(roles) != null
                ? this.roleRepository.findByName(roles)
                : null;

        if (alreadyAvailableRole != null) {
            responseData.put("role", alreadyAvailableRole);
            response.setResponseCode(AppConstants.FOUND);
            response.setResponseMessage(AppConstants.MSG_ROLE_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        role = new Role(roles);

        this.roleRepository.save(role);

        responseData.put("role", role);
        response.setResponseCode(AppConstants.CREATED);
        response.setResponseMessage(AppConstants.MSG_ROLE_SAVED_SUCCESSFULLY);
        response.setResponseData(responseData);

        logger.info("in UserServiceImpl.saveRole() : {} - end");

        return response;
    }

    @Override
    public Response addRoleToUser(Map<String, Object> input) {
        logger.info("in UserServiceImpl.addRoleToUser() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String email = input.get("email") != null ? (String) input.get("email") : null;
        String roles = input.get("role") != null ? (String) input.get("role") : null;

        User user;
        Role role;

        if (email == null || email.isEmpty()) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_EMAIL_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }
        if (roles == null || roles.isEmpty()) {
            responseData.put("role", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ROLE_PROVIDED);
            response.setResponseData(responseData);
            return response;
        }

        user = this.userRepository.findByEmail(email) != null ? this.userRepository.findByEmail(email) : null;
        role = this.roleRepository.findByName(roles) != null ? this.roleRepository.findByName(roles) : null;

        if (user == null) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_EMAIL_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        if (role == null) {
            responseData.put("role", null);
            response.setResponseCode(AppConstants.NOT_FOUND);
            response.setResponseMessage(AppConstants.MSG_NO_ROLE_AVAILABLE);
            response.setResponseData(responseData);
            return response;
        }

        user.getRoles().add(role);

        responseData.put("role", role);
        response.setResponseCode(AppConstants.OK);
        response.setResponseMessage(AppConstants.MSG_ROLE_ADDED_TO_USER);
        response.setResponseData(responseData);

        return response;
    }
}
