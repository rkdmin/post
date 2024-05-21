package com.postproject.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
}
