package com.hiro.blog.post.infrastructure;

import com.hiro.blog.post.domain.Post;
import com.hiro.blog.post.domain.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPostRepository extends PostRepository, JpaRepository<Post, UUID> {
}
