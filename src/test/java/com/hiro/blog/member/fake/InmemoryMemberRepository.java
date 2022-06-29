package com.hiro.blog.member.fake;

import com.hiro.blog.member.domain.Member;
import com.hiro.blog.member.domain.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InmemoryMemberRepository implements MemberRepository {

    private final Map<UUID, Member> members = new HashMap();

    @Override
    public Member save(Member member) {
        members.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return members.values().stream()
                .filter(member -> member.getUsername().equals(username))
                .findAny();
    }

    @Override
    public boolean existsByUsername(String username) {
        return members.values().stream()
                .anyMatch(member -> member.getUsername().equals(username));
    }

    @Override
    public boolean existsById(UUID id) {
        return members.values().stream()
                .anyMatch(member -> member.getId().equals(id));
    }

    @Override
    public Optional<Member> findById(UUID id) {
        return members.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }

}
