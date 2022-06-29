package com.hiro.blog.post.presentation.dtos;

import com.hiro.blog.post.application.dtos.WritePostCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritePostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public WritePostCommand toCommand(final UUID writerId) {
        return new WritePostCommand(writerId, title, content);
    }
}
