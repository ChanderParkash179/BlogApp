package com.app.blog.Model;

import com.app.blog.Utils.ModelConstants;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = ModelConstants.ROLE_NAME)
    private String name;
}
