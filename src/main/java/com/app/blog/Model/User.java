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

    @NotEmpty
    @Size(min = 3, max = 20, message = AppConstants.MINMAX_NAME)
    @Column(name = ModelConstants.USER_NAME)
    private String name;

    @Email(message = AppConstants.FORMAT_EMAIL)
    @Column(name = ModelConstants.USER_EMAIL)
    private String email;

    @NotEmpty
    @Size(min = 6, max = 12, message = AppConstants.MINMAX_PASSWORD)
    @Column(name = ModelConstants.USER_PASSWORD)
    private String password;

    @NotEmpty
    @Size(min = 10, max = 60, message = AppConstants.MINMAX_ABOUT)
    @Column(name = ModelConstants.USER_ABOUT, length = 500)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    public User(String name, String email, String password, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}