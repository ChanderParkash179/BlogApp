package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POST")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 20, message = AppConstants.MINMAX_TITLE)
    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content", length = 500)
    private String content;

    @Column(name = "post_imgUrl")
    private String imageUrl;

    @Column(name = "post_addedDate")
    private String addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

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