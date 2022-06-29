package com.hiro.blog.post.application;

import com.hiro.blog.member.domain.Member;
import com.hiro.blog.member.domain.MemberRepository;
import com.hiro.blog.member.domain.exception.MemberNotFoundException;
import com.hiro.blog.member.fake.InmemoryMemberRepository;
import com.hiro.blog.post.application.dtos.WritePostCommand;
import com.hiro.blog.post.domain.Post;
import com.hiro.blog.post.domain.PostRepository;
import com.hiro.blog.post.domain.exception.InvalidWriterDeletePostException;
import com.hiro.blog.post.domain.exception.PostNotFoundException;
import com.hiro.blog.post.fake.InmemoryPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.hiro.blog.member.fixtures.MemberFixtures.member;
import static com.hiro.blog.post.fixtures.PostFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PostServiceTest {

    private PostService postService;
    private PostRepository postRepository;
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        postRepository = new InmemoryPostRepository();
        memberRepository = new InmemoryMemberRepository();
        postService = new PostService(postRepository, memberRepository);
    }

    @DisplayName("게시글 저장")
    @Nested
    class Write {

        @DisplayName("게시글을 저장한다.")
        @Test
        void create_post() {
            // Arrange - 게시글을 작성할 회원이 존재함.
            Member member = member();
            memberRepository.save(member);
            WritePostCommand writePostCommand = writePostCommand(member.getId());

            // Act
            // Assert
            assertAll(
                    () -> assertDoesNotThrow(() -> postService.write(writePostCommand)),
                    () -> assertThat(postService.write(writePostCommand)).isNotNull()
            );
        }

        /**
         * Arrange - 게시글을 작성할 회원이 존재하지 않음
         * Act - 게시글을 작성함
         * Assert - 예외
         */
        @DisplayName("게시글 저장시 작성자의 id를 찾을 수 없는 경우 예외")
        @Test
        void not_found_writer_id() {
            // Arrange
            WritePostCommand writePostCommand = writePostCommand(UUID.randomUUID());

            // Act & Assert
            assertThatThrownBy(()-> postService.write(writePostCommand)).isInstanceOf(MemberNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("게시글 수정")
    class Modify {
        /**
         * Arrange
         * 게시글을 작성할 회원이 존재함
         * 회원이 게시글을 작성함.
         *
         * Act
         * 게시글을 작성한 회원이 게시글 수정
         *
         * Assert
         * 게시글이 수정됨
         */
        @DisplayName("게시글을 수정한다.")
        @Test
        void modify_post() {
            // Arrange
            UUID writerId = 회원_생성_요청();
            UUID postId = 게시글_작성(writerId);

            // Act
            // Assert
            assertDoesNotThrow(() -> postService.modify(writerId, postId, modifyPostCommand()));
        }

        @DisplayName("게시글 수정시, 수정을 요청한 회원을 찾을 수 없는 경우 예외")
        @Test
        void modify_member_not_found() {
            // Arrange
            Post post = post();
            postRepository.save(post);

            // Act & Assert
            assertThatThrownBy(()-> postService.modify(UUID.randomUUID(), post.getId(), modifyPostCommand())).isInstanceOf(InvalidWriterDeletePostException.class);
        }

        /**
         * Arrange
         * 게시글을 작성할 회원 존재
         *
         * Act
         * 존재하지 않는 게시글에대한 수정 요청
         *
         * Assert
         * 예외
         */
        @DisplayName("게시글 수정시, 수정할 게시글 id를 찾을 수 없는 경우 예외")
        @Test
        void modify_post_not_found() {
            // Arrange - 게시글을 작성할 회원이 존재
            UUID writerId = 회원_생성_요청();

            // Act & Assert
            assertThatThrownBy(() -> postService.modify(writerId, UUID.randomUUID(), modifyPostCommand())).isInstanceOf(PostNotFoundException.class);
        }
    }

    @DisplayName("게시글 삭제")
    @Nested
    class Delete {

        @DisplayName("게시글을 삭제 한다.")
        @Test
        void delete_post() {
            UUID writerId = 회원_생성_요청();
            UUID postId = 게시글_작성(writerId);

            assertDoesNotThrow(() -> postService.delete(writerId, postId));
        }

        @DisplayName("삭제할 게시글의 id를 찾을 수 없는 경우 예외")
        @Test
        void not_found_delete_post_id() {
            UUID writerId = 회원_생성_요청();

            assertThatThrownBy(() -> postService.delete(writerId, UUID.randomUUID()))
                    .isInstanceOf(PostNotFoundException.class);

        }

        @DisplayName("삭제할 게시글의 작성자와 삭제를 요청한 사용자의 id가 다를 경우 예외")
        @Test
        void not_found_writer_id() {
            UUID writerId = 회원_생성_요청();
            UUID postId = 게시글_작성(writerId);

            assertThatThrownBy(() -> postService.delete(UUID.randomUUID(), postId))
                    .isInstanceOf(InvalidWriterDeletePostException.class);
        }
    }

    private UUID 게시글_작성(final UUID writerId) {
        WritePostCommand writePostCommand = writePostCommand(writerId);

        return postService.write(writePostCommand);
    }

    private UUID 회원_생성_요청() {
        Member member = member();
        memberRepository.save(member);

        return member.getId();
    }

}