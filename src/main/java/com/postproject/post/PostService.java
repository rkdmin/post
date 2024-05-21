package com.postproject.post;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream()
                .map(PostDto::toDto)
                .toList();
    }

    public PostDto getPost(Long id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception("해당 게시글이 없습니다."));

        return PostDto.toDto(post);
    }
}
