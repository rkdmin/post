package com.postproject.reply;

import com.postproject.post.Post;
import com.postproject.post.PostDto;
import com.postproject.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ReplyDto> getAllReplies(Long postId) throws Exception {
        // 해당 게시글이 없는 경우
        if (!postRepository.existsById(postId)) {
            throw new Exception("해당 게시글이 없습니다.");
        };

        // DB에서 postId로 댓글들을 찾아옴
        List<Reply> replies = replyRepository.findByPost_Id(postId);

        // 댓글들을 Dto로 변환하여 반환
        List<ReplyDto> replyDtos = replies.stream()
                .map(reply -> ReplyDto.toDto(reply, reply.getPost().getId()))
                .collect(Collectors.toList());

        return replyDtos;
    }
}
