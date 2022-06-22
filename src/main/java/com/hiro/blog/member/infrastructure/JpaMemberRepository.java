package com.hiro.blog.member.infrastructure;

import com.hiro.blog.member.domain.Member;
import com.hiro.blog.member.domain.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMemberRepository extends MemberRepository, JpaRepository<Member, UUID> {
}
