package com.postproject.post;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDto create(PostDto request) throws Exception {
        // 제목이나 내용이 없으면 오류
        if (Strings.isBlank(request.getTitle()) || Strings.isBlank(request.getContent())) {
            throw new Exception("제목이나 내용이 없습니다.");
        }

        // 엔티티로 변경
        Post post = Post.toEntity(request);

        // DB에 저장
        return PostDto.toDto(postRepository.save(post));
    }
}
