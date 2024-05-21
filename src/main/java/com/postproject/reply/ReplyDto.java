package com.postproject.reply;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
    private Long id;
    private Long postId;
    private String content;

    public static ReplyDto toDto(Reply reply, Long postId) {
        return ReplyDto.builder()
                .id(reply.getId())
                .postId(postId)
                .content(reply.getContent())
                .build();
    }
}
