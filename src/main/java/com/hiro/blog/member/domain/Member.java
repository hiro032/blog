package com.hiro.blog.member.domain;

import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Member {

    @Id
    private UUID id;

    private String name;

    private String email;

    private String password;

    protected Member() {
    }

    public Member(final String name, final String email, final String password) {
        validate(name, email, password);
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    private void validate(final String name, final String email, final String password) {
        if (Strings.isBlank(name)) {
            throw new IllegalArgumentException();
        }
        if (Strings.isBlank(email)) {
            throw new IllegalArgumentException();
        }
        if (Strings.isBlank(password)) {
            throw new IllegalArgumentException();
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
