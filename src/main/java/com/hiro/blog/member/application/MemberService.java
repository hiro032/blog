package com.hiro.blog.member.application;

import com.hiro.blog.member.application.dtos.MemberCommand;
import com.hiro.blog.member.application.dtos.MemberInfo;
import com.hiro.blog.member.domain.Member;
import com.hiro.blog.member.domain.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder encoder;

    public MemberService(MemberRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public MemberInfo.CreateMemberInfo save(final MemberCommand.CreateMemberCommand command) {
        validateDuplicateUsername(command.getUsername());

        String encodePassword = encoder.encode(command.getPassword());

        Member member = new Member(command.getName(), command.getUsername(), encodePassword);

        return MemberInfo.CreateMemberInfo.fromEntity(repository.save(member));
    }

    private void validateDuplicateUsername(final String username) {
        if (repository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 가입된 아이디 입니다.");
        }
    }

}
