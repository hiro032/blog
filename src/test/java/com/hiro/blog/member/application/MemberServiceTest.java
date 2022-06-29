package com.hiro.blog.member.application;

import com.hiro.blog.member.domain.MemberRepository;
import com.hiro.blog.member.domain.exception.DuplicateUsernameException;
import com.hiro.blog.member.fake.InmemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.hiro.blog.member.fixtures.MemberFixtures.createMemberCommand;
import static com.hiro.blog.member.fixtures.MemberFixtures.member;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest {

    private MemberService service;
    private MemberRepository repository;
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        repository = new InmemoryMemberRepository();
        encoder = new BCryptPasswordEncoder();
        service = new MemberService(repository, encoder);
    }

    @DisplayName("중복된 아이디로 회원가입시 예외.")
    @Test
    void duplicate_username_signup() {
        // Arrange - 회원 등록
        String duplicateId = "duplicate";
        repository.save(member(duplicateId));

        // Act - 중복 아이디의 회원 등록 시도
        // Assert - DuplicateUsernameException
        assertThatThrownBy(() -> service.save(createMemberCommand(duplicateId)))
                .isInstanceOf(DuplicateUsernameException.class);
    }

}