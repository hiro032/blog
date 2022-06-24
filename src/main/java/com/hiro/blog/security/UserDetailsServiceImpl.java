package com.hiro.blog.security;

import com.hiro.blog.member.domain.Member;
import com.hiro.blog.member.domain.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository repository;

    public UserDetailsServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("아이디에 해당하는 회원을 찾을 수 없습니다."));

        return User.withUsername(member.getUsername()).build();
    }
}
