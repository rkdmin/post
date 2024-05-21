package com.postproject.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostDto createPost(@RequestBody PostDto request) throws Exception {
        return postService.createPost(request);
    }

    @GetMapping
    public List<PostDto> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) throws Exception {
        return postService.getPost(id);
    }

    @PatchMapping("/{id}")
    public PostDto editPost(@PathVariable Long id, @RequestBody PostDto postDto) throws Exception {
        return postService.editPost(id, postDto);
    }

}
