package com.postproject.post;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDto createPost(PostDto request) throws Exception {
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
        // DB에서 모든 게시글 조회
        return postRepository.findAll().stream()
                .map(PostDto::toDto)
                .toList();
    }

    public PostDto getPost(Long id) throws Exception {
        // DB의 해당 id 게시글이 없으면 에러
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception("해당 게시글이 없습니다."));

        // DB에서 가져온 엔티티 Dto로 변환하여 반환
        return PostDto.toDto(post);
    }

    @Transactional// 조회와 저장 Transaction으로 묶어준다.
    public PostDto editPost(Long id, PostDto request) throws Exception {
        // DB의 해당 id 게시글이 없으면 에러
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception("해당 게시글이 없습니다."));

        // 게시글 수정
        post.edit(request);

        // 게시글 저장후 반환
        return PostDto.toDto(postRepository.save(post));
    }

    @Transactional
    public PostDto deletePost(Long id) throws Exception {
        // DB의 해당 id 게시글이 없으면 에러
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception("해당 게시글이 없습니다."));

        // 게시글 삭제
        postRepository.delete(post);

        // 삭제된 게시글 Dto 변환하여 반환
        return PostDto.toDto(post);
    }
}
