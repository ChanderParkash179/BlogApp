package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = AppConstants.COMMENT_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = AppConstants.COMMENT_ID)
    private Long id;

    @NotEmpty
    @Size(min = 6, max = 60, message = AppConstants.MINMAX_CONTENT)
    @Column(name = AppConstants.COMMENT_CONTENT, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = AppConstants.POST_ID)
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = AppConstants.USER_ID)
    @JsonIgnore
    private User user;

    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

}
