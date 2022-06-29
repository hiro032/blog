package com.hiro.blog.post.fixtures;

import com.hiro.blog.post.application.dtos.ModifyPostCommand;
import com.hiro.blog.post.application.dtos.WritePostCommand;
import com.hiro.blog.post.domain.Post;
import com.hiro.blog.post.presentation.dtos.ModifyPostRequest;

import java.util.UUID;

public class PostFixtures {
    public static final UUID DEFAULT_WRITER_ID = UUID.randomUUID();
    public static final String DEFAULT_WRITER_NAME = "hiro";
    public static final String DEFAULT_TITLE = "my title";
    public static final String DEFAULT_CONTENTS = "my conetnt";

    public static final String MODIFY_TITLE = "modify title";
    public static final String MODIFY_CONTENTS = "modify contents";

    public static ModifyPostRequest modifyPostRequest() {
        return new ModifyPostRequest(DEFAULT_TITLE, DEFAULT_CONTENTS);
    }

    public static ModifyPostCommand modifyPostCommand() {
        return new ModifyPostCommand(MODIFY_TITLE, MODIFY_CONTENTS);
    }

    public static Post post() {
        return new Post(DEFAULT_WRITER_ID, DEFAULT_WRITER_NAME, DEFAULT_TITLE, DEFAULT_CONTENTS);
    }

    public static WritePostCommand writePostCommand() {
        return new WritePostCommand(DEFAULT_WRITER_ID, DEFAULT_TITLE, DEFAULT_CONTENTS);
    }

    public static WritePostCommand writePostCommand(final UUID writerId) {
        return new WritePostCommand(writerId, DEFAULT_TITLE, DEFAULT_CONTENTS);
    }
}
