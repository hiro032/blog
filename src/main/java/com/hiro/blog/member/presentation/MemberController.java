package com.hiro.blog.member.presentation;

import com.hiro.blog.member.application.MemberService;
import com.hiro.blog.member.application.dtos.MemberInfo;
import com.hiro.blog.member.presentation.dtos.LoginUserInfo;
import com.hiro.blog.member.presentation.dtos.MemberRequest;
import com.hiro.blog.security.LoginUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/members")
    public ResponseEntity<Void> save(@RequestBody @Valid final MemberRequest request) {
        MemberInfo.CreateMemberInfo info = service.save(request.toCommand());

        return ResponseEntity.created(URI.create("/members/" + info.getId())).build();
    }

    @GetMapping("/me")
    public ResponseEntity<LoginUserInfo> getLoginUserInfo(@AuthenticationPrincipal LoginUser user) {

        return ResponseEntity.ok(new LoginUserInfo(user.getId(), user.getUsername()));
    }
}
