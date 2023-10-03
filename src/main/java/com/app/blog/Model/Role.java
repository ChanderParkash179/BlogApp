package com.app.blog.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.app.blog.Utils.AppConstants;
import com.app.blog.Utils.ModelConstants;

import lombok.*;

@Entity
@Table(name = ModelConstants.ROLE_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ModelConstants.ROLE_ID)
    private Long id;

    @NotEmpty(message = AppConstants.VALID_ROLE)
    @Column(name = ModelConstants.ROLE_NAME)
    private String name;

    public Role(String name) {
        this.name = name;
    }

}
