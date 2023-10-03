package com.app.blog.Security.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // get Token
        String requestToken = request.getHeader("Authorization");
        logger.info("TOKEN ==> " + requestToken);
        String username = null, token = null;

        if (requestToken != null && requestToken.startsWith("Bearer ")) {
            token = requestToken.substring(7);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token!", e);
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired!", e);
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT!", e);
            }
        } else {
            logger.info("JWT Token doesn't start with \"Bearer\" ");
        }

        // validate the token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities());
                userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            } else {
                logger.info("Invalid JWT Token!");
            }
        } else {
            logger.info("Username is null -OR- Context is not null");
        }
        filterChain.doFilter(request, response);
    }


    /* @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // get Token

        String requestToken = request.getHeader("Authorization");
        System.out.println("TOKEN ==> " + requestToken);

        String username = null, token = null;

        if (requestToken != null && requestToken.startsWith("Bearer ")) {

            token = requestToken.substring(7);

            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token!");
                logger.error(e);
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired!");
                logger.error(e);
            } catch (MalformedJwtException e) {
                System.out.println("Invalid JWT!");
                logger.error(e);
            }
        } else {
            System.out.println("JWT Token doesn't starts with \"Bearer\" ");
        }

        // validate the token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (this.jwtTokenHelper.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities());

                userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(null);
            } else {
                System.out.println("Invalid JWT Token!");
            }

        } else {
            System.out.println("Username is null -OR- Context is not null");
        }

        filterChain.doFilter(request, response);

    }*/

}
