package com.hiro.blog.member.domain;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
public class Member {

    @Id
    @Column(columnDefinition = "varbinary(16)")
    private UUID id;

    private String name;

    private String username;

    private String password;

    protected Member() {
    }

    public Member(final String name, final String username, final String password) {
        validate(name, username, password);
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.password = password;
    }

    private void validate(final String name, final String username, final String password) {
        if (Strings.isBlank(name)) {
            throw new IllegalArgumentException();
        }
        if (Strings.isBlank(username)) {
            throw new IllegalArgumentException();
        }
        if (Strings.isBlank(password)) {
            throw new IllegalArgumentException();
        }
    }

}
