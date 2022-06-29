package com.hiro.blog.post.presentation;

import com.hiro.blog.post.application.PostService;
import com.hiro.blog.post.presentation.dtos.ModifyPostRequest;
import com.hiro.blog.post.presentation.dtos.WritePostRequest;
import com.hiro.blog.security.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> writePost(@AuthenticationPrincipal LoginUser user, @RequestBody @Valid WritePostRequest request) {
        UUID postId = service.write(request.toCommand(user.getId()));

        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@AuthenticationPrincipal LoginUser user, @PathVariable UUID id) {
        service.delete(user.getId(), id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Void> modifyPost(@AuthenticationPrincipal LoginUser user, @PathVariable UUID id, @RequestBody @Valid ModifyPostRequest request) {
        service.modify(user.getId(), id, request.toCommand());

        return ResponseEntity.ok().build();
    }

}
