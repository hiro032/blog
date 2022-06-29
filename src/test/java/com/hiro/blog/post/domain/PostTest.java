package com.hiro.blog.post.domain;

import com.hiro.blog.post.domain.exception.InvalidWriterDeletePostException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostTest {

    @DisplayName("Post 생성시 식별자가 할당된다.")
    @Test
    void create() {
        Post post = new Post(UUID.randomUUID(), "hiro", "my title", "my content");

        assertThat(post.getId()).isNotNull();
    }

    @DisplayName("작성자가 아닌 회원이 게시물 삭제시 예외가 발생한다.")
    @Test
    void delete_post_by_other_user() {
        Post post = new Post(UUID.randomUUID(), "hiro", "my title", "my content");

        assertThatThrownBy(() -> post.validateWriter(UUID.randomUUID())).isInstanceOf(InvalidWriterDeletePostException.class);
    }
}