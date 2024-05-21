package com.postproject.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createReply(@RequestBody ReplyDto request) throws Exception {
        return replyService.createReply(request);
    }

    @GetMapping
    public ReplyDto getAllReplies(@RequestBody ReplyDto request) throws Exception {
        return replyService.createReply(request);
    }


}
