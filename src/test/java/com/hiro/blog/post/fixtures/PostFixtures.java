package com.hiro.blog.post.fixtures;

import com.hiro.blog.post.presentation.dtos.ModifyPostRequest;

public class PostFixtures {
    public static ModifyPostRequest modifyPostRequest() {
        return new ModifyPostRequest("new post title", "new post content");
    }
}
