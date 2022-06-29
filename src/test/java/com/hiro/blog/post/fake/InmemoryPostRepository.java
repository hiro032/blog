package com.hiro.blog.post.fake;

import com.hiro.blog.post.domain.Post;
import com.hiro.blog.post.domain.PostRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InmemoryPostRepository implements PostRepository {

    private final Map<UUID, Post> posts = new HashMap<>();

    @Override
    public Post save(Post post) {
        posts.put(post.getId(), post);

        return post;
    }

    @Override
    public void deleteById(UUID id) {
        posts.remove(id);
    }

    @Override
    public Optional<Post> findById(UUID id) {
        return posts.values().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
    }
}
