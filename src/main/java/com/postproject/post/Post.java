package com.postproject.post;

import io.micrometer.common.util.StringUtils;
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

    public void edit(PostDto request) {
        // 제목이 있으면 변경
        if (StringUtils.isNotBlank(request.getTitle())) {
            this.title = request.getTitle();
        }
        // 내용이 있으면 변경
        if (StringUtils.isNotBlank(request.getContent())) {
            this.contents = request.getContent();
        }
    }
}
