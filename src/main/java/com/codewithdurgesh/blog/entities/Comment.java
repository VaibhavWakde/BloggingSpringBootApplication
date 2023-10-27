package com.codewithdurgesh.blog.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
