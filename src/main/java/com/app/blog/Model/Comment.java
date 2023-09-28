package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.app.blog.Utils.ModelConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = ModelConstants.COMMENT_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ModelConstants.COMMENT_ID)
    private Long id;

    @NotEmpty(message = AppConstants.MINMAX_CONTENT)
    @Column(name = ModelConstants.COMMENT_CONTENT, length = AppConstants.NUM_500)
    private String content;

    @ManyToOne
    @JoinColumn(name = ModelConstants.POST_ID)
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = ModelConstants.USER_ID)
    @JsonIgnore
    private User user;

    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

}
