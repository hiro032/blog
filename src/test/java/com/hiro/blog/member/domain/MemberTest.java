package com.hiro.blog.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @DisplayName("Member 생성시 식별자(UUID)가 할당된다.")
    @Test
    void createMember() {
        Member member = new Member("user name", "my@email.com", "1234");

        assertThat(member.getId()).isNotNull();
    }

    @DisplayName("name 없이 Member 생성시 예외.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_member_without_name(final String name) {
        assertThatThrownBy(() -> new Member(name, "my@email.com", "1234"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("email 없이 Member 생성시 예외.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_member_without_email(final String email) {
        assertThatThrownBy(() -> new Member("hiro",  email, "1234"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("password 없이 Member 생성시 예외.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_member_without_pwd(final String pwd) {
        assertThatThrownBy(() -> new Member("hiro", "my@email.com", pwd))
                .isInstanceOf(IllegalArgumentException.class);
    }
}