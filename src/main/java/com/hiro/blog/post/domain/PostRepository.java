package com.hiro.blog.post.domain;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository {
    Post save(Post post);

    void deleteById(UUID id);

    Optional<Post> findById(UUID id);
}
