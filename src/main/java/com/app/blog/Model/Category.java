package com.app.blog.Model;

import com.app.blog.Utils.AppConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 12, message = AppConstants.MINMAX_TITLE)
    @Column(name = "category_title", nullable = false, length = 100)
    private String title;

    @NotEmpty
    @Size(min = 6, max = 100, message = AppConstants.MINMAX_DESCRIPTION)
    @Column(name = "category_description")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }
}