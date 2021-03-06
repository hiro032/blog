package com.hiro.blog.post.domain;

import com.hiro.blog.post.fixtures.PostFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PostTest {

    @DisplayName("Post 생성시 식별자가 할당된다.")
    @Test
    void create() {
        Post post = new Post(UUID.randomUUID(), "hiro", "my title", "my content");

        assertThat(post.getId()).isNotNull();
    }

    @DisplayName("게시글 수정시 값이 대체 된다.")
    @Test
    void modify_post() {
        // Arrange - 게시글
        Post post = PostFixtures.post();

        String modifiedTitle = "modify title";
        String modifiedContents = "modify contents";

        // Act - 게시글 수정
        post.modify(modifiedTitle, modifiedContents);

        // Assert
        assertAll(
                () -> assertThat(post.getTitle()).isEqualTo(modifiedTitle),
                () -> assertThat(post.getContent()).isEqualTo(modifiedContents)
        );
    }
}