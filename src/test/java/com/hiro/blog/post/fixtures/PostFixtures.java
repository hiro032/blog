package com.hiro.blog.post.fixtures;

import com.hiro.blog.post.domain.Post;
import com.hiro.blog.post.presentation.dtos.ModifyPostRequest;

import java.util.UUID;

public class PostFixtures {
    public static final UUID DEFAULT_WRITER_ID = UUID.randomUUID();
    public static final String DEFAULT_WRITER_NAME = "hiro";
    public static final String DEFAULT_TITLE = "my title";
    public static final String DEFAULT_CONTENTS = "my conetnt";

    public static ModifyPostRequest modifyPostRequest() {
        return new ModifyPostRequest(DEFAULT_TITLE, DEFAULT_CONTENTS);
    }

    public static Post post() {
        return new Post(DEFAULT_WRITER_ID, DEFAULT_WRITER_NAME, DEFAULT_TITLE, DEFAULT_CONTENTS);
    }
}
