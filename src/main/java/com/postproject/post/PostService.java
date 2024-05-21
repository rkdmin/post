package com.postproject.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDto create(PostDto request) {
        Post post = Post.toEntity(request);

        return PostDto.toDto(postRepository.save(post));
    }
}
