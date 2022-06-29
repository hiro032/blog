package com.hiro.blog.post.presentation.dtos;

import com.hiro.blog.post.application.dtos.ModifyPostCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ModifyPostRequest {

    private String title;
    private String content;

    public ModifyPostCommand toCommand() {
        return new ModifyPostCommand(title, content);
    }
}
