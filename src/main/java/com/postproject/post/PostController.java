package com.postproject.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostDto createPost(@RequestBody PostDto request) throws Exception {
        return postService.create(request);
    }

    @GetMapping
    public List<PostDto> getAllPost() throws Exception {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) throws Exception {
        return postService.getPost(id);
    }

}
