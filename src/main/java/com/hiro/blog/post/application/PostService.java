package com.hiro.blog.post.application;

import com.hiro.blog.member.domain.Member;
import com.hiro.blog.member.domain.MemberRepository;
import com.hiro.blog.post.application.dtos.ModifyPostCommand;
import com.hiro.blog.post.application.dtos.WritePostCommand;
import com.hiro.blog.post.domain.Post;
import com.hiro.blog.post.domain.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public UUID write(final WritePostCommand command) {
        Member writer = findWriter(command.getWriterId());

        Post post = command.toEntity(writer.getName());

        return postRepository.save(post).getId();
    }

    private Member findWriter(final UUID writerId) {
        return memberRepository.findById(writerId)
                .orElseThrow(() -> new NoSuchElementException(writerId + "아이디에 해당하는 유저를 찾을 수 없습니다."));
    }

    @Transactional
    public void delete(final UUID writerId, final UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);

        post.validateWriter(writerId);

        postRepository.deleteById(postId);
    }

    @Transactional
    public void modify(final UUID writerId, final UUID postId, final ModifyPostCommand command) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);

        post.modify(writerId, command.getTitle(), command.getContent());
    }
}
