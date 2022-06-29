package com.hiro.blog.member.application;

import com.hiro.blog.member.application.dtos.MemberCommand;
import com.hiro.blog.member.application.dtos.MemberInfo;
import com.hiro.blog.member.domain.MemberRepository;
import com.hiro.blog.member.domain.exception.DuplicateUsernameException;
import com.hiro.blog.member.fake.InmemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.hiro.blog.member.fixtures.MemberFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @DisplayName("회원 가입.")
    @Test
    void save() {
        // Arrange - 회원 가입 정보
        MemberCommand.CreateMemberCommand command = createMemberCommand();

        // Act - 회원 가입 요청시
        MemberInfo.CreateMemberInfo info = service.save(command);

        // Assert - 회원 가입 정보를 기반으로 info 응답
        assertAll(
                () -> assertThat(info.getId()).isNotNull(),
                () -> assertThat(info.getName()).isEqualTo(DEFAULT_NAME),
                () -> assertThat(info.getUsername()).isEqualTo(DEFAULT_USERNAME)
        );
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