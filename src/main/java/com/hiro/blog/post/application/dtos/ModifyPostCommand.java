package com.hiro.blog.post.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModifyPostCommand {

    private String title;
    private String content;

}
