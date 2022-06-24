package com.hiro.blog.member.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
}
