package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.app.blog.Utils.ModelConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

@Entity
@Table(name = ModelConstants.USER_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {

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

    @NotEmpty(message = AppConstants.VALID_SOURCE)
    @Column(name = ModelConstants.USER_SOURCE)
    private String source;

    @OneToMany(mappedBy = ModelConstants.USER, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = ModelConstants.USER, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @NotEmpty(message = AppConstants.VALID_ROLE)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = ModelConstants.USER_ROLE, joinColumns = @JoinColumn(name = ModelConstants.USER, referencedColumnName = ModelConstants.USER_ID), inverseJoinColumns = @JoinColumn(name = ModelConstants.ROLE, referencedColumnName = ModelConstants.ROLE_ID))
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String lastName, String email, String password, String about, String source,
            Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.about = about;
        this.source = source;
        this.roles = roles;
    }

    public User(String firstName, String lastName, String email, String password, String about, String source) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.about = about;
        this.source = source;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}