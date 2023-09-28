package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.app.blog.Utils.ModelConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ModelConstants.USER_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ModelConstants.USER_ID)
    private Long id;

    @NotEmpty(message = AppConstants.MINMAX_NAME)
    @Column(name = ModelConstants.USER_FIRST_NAME)
    private String firstName;

    @NotEmpty(message = AppConstants.MINMAX_NAME)
    @Column(name = ModelConstants.USER_LAST_NAME)
    private String lastName;

    @Email(message = AppConstants.FORMAT_EMAIL)
    @Column(name = ModelConstants.USER_EMAIL)
    private String email;

    @NotEmpty(message = AppConstants.MINMAX_PASSWORD)
    @Column(name = ModelConstants.USER_PASSWORD)
    private String password;

    @NotEmpty(message = AppConstants.MINMAX_ABOUT)
    @Column(name = ModelConstants.USER_ABOUT, length = AppConstants.NUM_500)
    private String about;

    @NotEmpty(message = AppConstants.VALID_ROLE)
    @Column(name = ModelConstants.USER_ROLE)
    private String role;

    @NotEmpty(message = AppConstants.VALID_SOURCE)
    @Column(name = ModelConstants.USER_SOURCE)
    private String source;

    @OneToMany(mappedBy = ModelConstants.USER, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = ModelConstants.USER, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    public User(String firstName, String lastName, String email, String password, String about, String role,
            String source) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.about = about;
        this.role = role;
        this.source = source;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}