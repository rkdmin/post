package com.postproject.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String contents;

    public static Post toEntity(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .contents(postDto.getContent())
                .build();
    }
}
