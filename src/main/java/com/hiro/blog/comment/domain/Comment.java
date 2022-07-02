package com.hiro.blog.comment.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
public class Comment {

    @Id
    @Column(columnDefinition = "varbinary(16)")
    private UUID id;

    private UUID postId;

    private UUID writerId;

    private String comment;


    protected Comment() {
    }

    public Comment(UUID postId, UUID writerId, String comment) {
        this.postId = postId;
        this.writerId = writerId;
        this.comment = comment;
    }
}
