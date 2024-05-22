package com.postproject.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createReply(@RequestBody ReplyDto request) throws Exception {
        return replyService.createReply(request);
    }

    @GetMapping("/{postId}")
    public List<ReplyDto> getAllReplies(@PathVariable Long postId) throws Exception {
        return replyService.getAllReplies(postId);
    }


}
