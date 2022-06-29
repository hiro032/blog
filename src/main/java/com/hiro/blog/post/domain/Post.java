package com.hiro.blog.post.domain;


import com.hiro.blog.post.domain.exception.InvalidWriterDeletePostException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
public class Post {

    @Id
    @Column(columnDefinition = "varbinary(16)")
    private UUID id;

    @Column(columnDefinition = "varbinary(16)")
    private UUID writerId;

    private String writerName;

    private String title;

    private String content;

    protected Post() {
        this.id = UUID.randomUUID();
    }

    public Post(UUID writerId, String writerName, String title, String content) {
        this.id = UUID.randomUUID();
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.content = content;
    }

    public void validateWriter(UUID writerId) {
        if (this.writerId.compareTo(writerId) != 0) {
            throw new InvalidWriterDeletePostException();
        }
    }

    public void modify(final UUID writerId, final String modifiedTitle, final String modifiedContent) {
        validateWriter(writerId);

        this.title = modifiedTitle;
        this.content = modifiedContent;
    }
}
