package com.hiro.blog.member.application;

import com.hiro.blog.member.application.dtos.MemberCommand;
import com.hiro.blog.member.application.dtos.MemberInfo;
import com.hiro.blog.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public MemberInfo.CreateMemberInfo save(final MemberCommand.CreateMemberCommand command) {
        return MemberInfo.CreateMemberInfo.fromEntity(repository.save(command.toEntity()));
    }
}
