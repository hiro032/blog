package com.hiro.blog.post.application.dtos;

import com.hiro.blog.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WritePostCommand {
    private UUID writerId;
    private String title;
    private String content;

    public Post toEntity(final String writerName) {
        return new Post(writerId, writerName, title, content);
    }
}
