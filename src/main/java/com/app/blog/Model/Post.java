package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.app.blog.Utils.ModelConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ModelConstants.POST_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ModelConstants.POST_ID)
    private Long id;

    @NotEmpty(message = AppConstants.MINMAX_TITLE)
    @Column(name = ModelConstants.POST_TITLE)
    private String title;

    @Column(name = ModelConstants.POST_CONTENT, length = AppConstants.NUM_500)
    private String content;

    @Column(name = ModelConstants.POST_IMG_URL)
    private String imageUrl;

    @Column(name = ModelConstants.POST_ADDED_DATE)
    private String addedDate;

    @ManyToOne
    @JoinColumn(name = ModelConstants.CATEGORY_ID)
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = ModelConstants.USER_ID)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = ModelConstants.POST, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, String imageUrl, String addedDate) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.addedDate = addedDate;
    }

    public Post(String title, String content, String imageUrl, String addedDate, Category category, User user) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.addedDate = addedDate;
        this.category = category;
        this.user = user;
    }
}