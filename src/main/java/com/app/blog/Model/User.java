package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 20, message = AppConstants.MINMAX_NAME)
    @Column(name = "user_name")
    private String name;

    @Email(message = AppConstants.FORMAT_EMAIL)
    @Column(name = "user_email")
    private String email;

    @NotEmpty
    @Size(min = 6, max = 12, message = AppConstants.MINMAX_PASSWORD)
    @Column(name = "user_password")
    private String password;

    @NotEmpty
    @Size(min = 10, max = 60, message = AppConstants.MINMAX_ABOUT)
    @Column(name = "user_about", length = 500)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();

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