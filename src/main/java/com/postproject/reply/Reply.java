package com.postproject.reply;

import com.postproject.post.Post;
import com.postproject.post.PostDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue
    private Long id;

    // Post와 다대일 설정
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private String content;

    public static Reply toEntity(ReplyDto replyDto, Post post) {
        return Reply.builder()
                .id(replyDto.getId())
                .post(post)
                .content(replyDto.getContent())
                .build();
    }
}
