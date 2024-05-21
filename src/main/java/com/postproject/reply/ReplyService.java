package com.postproject.reply;

import com.postproject.post.Post;
import com.postproject.post.PostDto;
import com.postproject.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Transactional
    public ReplyDto createReply(ReplyDto request) throws Exception {
        // 댓글 내용이 없으면 오류
        if (Strings.isBlank(request.getContent())) {
            throw new Exception("내용이 없습니다.");
        }

        // 해당 게시글이 없으면 오류
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new Exception("해당 게시글이 없습니다."));

        // 엔티티로 변경 (Post 엔티티도 넣어줌)
        Reply reply = Reply.toEntity(request, post);

        // DB에 저장하고 Dto로 변환하여 반환
        return ReplyDto.toDto(replyRepository.save(reply), post.getId());
    }
}
